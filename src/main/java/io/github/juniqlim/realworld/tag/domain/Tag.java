package io.github.juniqlim.realworld.tag.domain;

import io.github.juniqlim.realworld.Id;

import java.util.List;
import java.util.Objects;

public class Tag {
    private final String value;
    private final List<Id> articleIds;

    public Tag(String value, List<Id> articleIds) {
        this.value = value;
        this.articleIds = articleIds;
    }

    public String value() {
        return value;
    }

    public List<Id> articleIds() {
        return articleIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tag tag = (Tag) o;
        return Objects.equals(value, tag.value) && Objects.equals(articleIds, tag.articleIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, articleIds);
    }
}
