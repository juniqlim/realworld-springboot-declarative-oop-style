package io.github.juniqlim.realworld.tag.domain;

import io.github.juniqlim.realworld.Id;

import java.util.List;
import java.util.Objects;

public class Tags {
    private final Id articleId;
    private final List<String> tags;

    public Tags(Id articleId, List<String> tags) {
        this.articleId = articleId;
        this.tags = tags;
    }

    public Id articleId() {
        return articleId;
    }

    public List<String> tags() {
        return tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tags tags1 = (Tags) o;
        return Objects.equals(articleId, tags1.articleId) && Objects.equals(tags, tags1.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleId, tags);
    }
}
