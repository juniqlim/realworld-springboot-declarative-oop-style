package io.github.juniqlim.realworld.article.domain;

import java.util.Objects;

class Slug {
    private final String raw;

    Slug(String raw) {
        this.raw = raw;
    }

    String value() {
        return raw.toLowerCase().replace(" ", "-");
    }

    boolean equalsString(String other) {
        return value().equals(other);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Slug slug = (Slug) o;
        return Objects.equals(raw, slug.raw);
    }

    @Override
    public int hashCode() {
        return Objects.hash(raw);
    }
}
