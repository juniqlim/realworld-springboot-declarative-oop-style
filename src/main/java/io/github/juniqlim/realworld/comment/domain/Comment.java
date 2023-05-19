package io.github.juniqlim.realworld.comment.domain;

import io.github.juniqlim.realworld.Id;
import java.time.LocalDateTime;

public class Comment {
    private final Id id;
    private final Id articleId;
    private final String body;
    private final Id userId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Comment(Id id, Id articleId, String body, Id userId) {
        this.id = id;
        this.articleId = articleId;
        this.body = body;
        this.userId = userId;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Id id() {
        return id;
    }

    public Id articleId() {
        return articleId;
    }

    public String body() {
        return body;
    }

    public Id userId() {
        return userId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
