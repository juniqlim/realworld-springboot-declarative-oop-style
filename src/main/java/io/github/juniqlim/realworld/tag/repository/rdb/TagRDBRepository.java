package io.github.juniqlim.realworld.tag.repository.rdb;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.tag.domain.Tags;
import io.github.juniqlim.realworld.tag.repository.TagRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
class TagRDBRepository implements TagRepository {
    private final TagJpaRepository tagJpaRepository;
    private final TagArticleJpaRepository tagArticleJpaRepository;

    public TagRDBRepository(TagJpaRepository tagJpaRepository, TagArticleJpaRepository tagArticleJpaRepository) {
        this.tagJpaRepository = tagJpaRepository;
        this.tagArticleJpaRepository = tagArticleJpaRepository;
    }

    @Override
    public List<String> merges(Tags tags) {
        List<TagEntity> foundTagEntities = tagJpaRepository.findByTagIn(tags.tags());
        List<String> newTagStrings = distinctTags(tags, foundTagEntities);

        List<TagEntity> newTagEntities = tagJpaRepository.saveAll(
            newTagStrings.stream()
                .map(TagEntity::new)
                .collect(Collectors.toList())
        );

        tagArticleJpaRepository.saveAll(
            Stream.concat(foundTagEntities.stream(), newTagEntities.stream())
                .map(tagEntity -> new TagArticleEntity(new TagArticleEntity.TagArticleId(tags.articleId().value(), tagEntity.id())))
                .collect(Collectors.toList())
        );
        return Stream.concat(foundTagEntities.stream(), newTagEntities.stream())
            .map(TagEntity::tag)
            .collect(Collectors.toList());
    }

    private List<String> distinctTags(Tags tags, List<TagEntity> savedTags) {
        return distinct(
            tags.tags(),
            savedTags.stream()
                .map(TagEntity::tag)
                .collect(Collectors.toList())
        );
    }

    private <T> List<T> distinct(List<T> target, List<T> source) {
        List<T> collect = target.stream().filter(o -> !source.contains(o))
            .distinct()
            .collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<String> findAll() {
        return tagJpaRepository.findAll().stream().map(TagEntity::tag).collect(Collectors.toList());
    }

    @Override
    public List<Id> findArticleIdsByTagString(String tag) {
        return tagArticleJpaRepository.findByIdTagId(
                tagJpaRepository.findByTag(tag).id()
            ).stream()
            .map(tagArticleEntity -> new Id.LongId(tagArticleEntity.id().articleId()))
            .collect(Collectors.toList());
    }

    @Override
    public List<String> findByArticleId(Id articleId) {
        List<Long> tagIds = tagArticleJpaRepository.findByIdArticleId(articleId.value()).stream()
            .map(tagArticleEntity -> tagArticleEntity.id().tagId())
            .collect(Collectors.toList());
        return tagJpaRepository.findByIdIn(tagIds).stream()
            .map(TagEntity::tag)
            .collect(Collectors.toList());
    }

    @Override
    public List<Tags> findByArticleIds(List<Id> articleIds) {
        List<TagArticleEntity> tagArticleEntities = tagArticleJpaRepository.findByIdArticleIdIn(
            articleIds.stream()
                .map(id -> ((Id.LongId) id).value())
                .collect(Collectors.toList())
        );

        List<TagEntity> tagEntities = tagJpaRepository.findByIdIn(
            tagArticleEntities.stream()
                .map(tagArticleEntity -> tagArticleEntity.id().tagId())
                .distinct()
                .collect(Collectors.toList())
        );

        return articleIds.stream()
            .map(articleId -> tags(tagArticleEntities, tagEntities, articleId))
            .collect(Collectors.toList());
    }

    private static Tags tags(List<TagArticleEntity> tagArticleEntities, List<TagEntity> tagEntities, Id articleId) {
        return new Tags(
            articleId,
            tagArticleEntities.stream()
                .filter(tagArticleEntity -> tagArticleEntity.id().articleId() == ((Id.LongId) articleId).value())
                .map(tagArticleEntity -> tag(tagEntities, tagArticleEntity))
                .filter(Objects::nonNull)
                .collect(Collectors.toList())
        );
    }

    private static String tag(List<TagEntity> tagEntities, TagArticleEntity tagArticleEntity) {
        return tagEntities.stream()
            .filter(tagEntity -> tagEntity.id() == tagArticleEntity.id().tagId())
            .map(TagEntity::tag)
            .findFirst()
            .orElse(null);
    }
}
