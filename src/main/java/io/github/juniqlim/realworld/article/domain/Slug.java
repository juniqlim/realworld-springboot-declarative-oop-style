package io.github.juniqlim.realworld.article.domain;

class Slug {
    private final String raw;

    Slug(String raw) {
        this.raw = raw;
    }

    String value() {
        return raw.toLowerCase().replace(" ", "-");
    }
}
