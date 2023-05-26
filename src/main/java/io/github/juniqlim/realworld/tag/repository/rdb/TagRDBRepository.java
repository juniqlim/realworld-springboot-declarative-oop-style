package io.github.juniqlim.realworld.tag.repository.rdb;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.tag.domain.Tags;
import io.github.juniqlim.realworld.tag.repository.TagRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
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
        List<TagEntity> findedTagEntities = tagJpaRepository.findByTagIn(tags.tags());
        List<String> newTagStrings = distinctTags(tags, findedTagEntities);

        List<TagEntity> newTagEntities = tagJpaRepository.saveAll(
            newTagStrings.stream()
                .map(TagEntity::new)
                .collect(Collectors.toList())
        );

        Stream<TagEntity> tagEntityStream = Stream.concat(findedTagEntities.stream(), newTagEntities.stream());

        tagArticleJpaRepository.saveAll(
            tagEntityStream
                .map(tagEntity -> new TagArticleEntity(new TagArticleEntity.TagArticleId(tags.articleId().value(), tagEntity.id())))
                .collect(Collectors.toList())
        );
        return tagEntityStream.map(TagEntity::tag).collect(Collectors.toList());
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
        return target.stream().filter(o -> !source.contains(o))
            .distinct()
            .collect(Collectors.toList());
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
}
