package io.github.juniqlim.realworld.article.domain;

import java.util.Objects;

public class Tag {
    private final String value;

    public Tag(String value) {
        this.value = value;
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
        return Objects.equals(value, tag.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public String value() {
        return value;
    }
}
