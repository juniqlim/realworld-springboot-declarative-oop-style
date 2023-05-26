package io.github.juniqlim.realworld.tag.domain;

import io.github.juniqlim.realworld.Id;

import java.util.List;

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
}
