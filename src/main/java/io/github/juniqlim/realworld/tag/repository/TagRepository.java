package io.github.juniqlim.realworld.tag.repository;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.tag.domain.Tag;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TagRepository {
    private final List<Tag> tags = new ArrayList<>();

    public void merge(Tag tag) {
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
}
