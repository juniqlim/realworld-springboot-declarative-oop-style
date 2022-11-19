package io.github.juniqlim.realworld.article.repository;

import io.github.juniqlim.realworld.article.domain.Tag;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

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

    public void delete(Tag tag) {
        tags.remove(tag);
    }
}
