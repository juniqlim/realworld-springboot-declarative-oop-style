package io.github.juniqlim.realworld.tag.repository;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.tag.domain.Tag;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class TagRepository {
    private final List<Tag> tags = new ArrayList<>();

    public void merge(Tag tag) {
        tags.stream()
            .filter(t -> t.value().equals(tag.value()))
            .findFirst()
            .ifPresent(
                t -> new Tag(
                    tag.value(),
                    Stream.concat(t.articleIds().stream(), tag.articleIds().stream()).distinct().collect(Collectors.toList())
                )
            );
        if (!tags.contains(tag)) {
            tags.add(tag);
        }
    }

    public void merges(List<Tag> tags) {
        tags.forEach(this::merge);
    }

    public List<Tag> findAll() {
        return tags;
    }

    public void deleteByTagString(String tag) {
        tags.stream()
                .filter(t -> t.value().equals(tag))
                .findFirst()
                .ifPresent(tags::remove);
    }

    public List<Id> findArticleIdsByTagString(String tag) {
        return tags.stream()
                .filter(t -> t.value().equals(tag))
                .findFirst()
                .map(Tag::articleIds)
                .orElse(new ArrayList<>());
    }

    public List<Tag> findByArticleId(Id articleId) {
        return tags.stream()
            .filter(t -> t.articleIds().contains(articleId))
            .collect(Collectors.toList());
    }
}
