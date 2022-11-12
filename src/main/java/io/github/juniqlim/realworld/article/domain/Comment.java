package io.github.juniqlim.realworld.article.domain;

import java.time.LocalDateTime;

public class Comment {
    private final long id;
    private final String body;
    private final String userId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Comment(long id, String body, String userId) {
        this.id = id;
        this.body = body;
        this.userId = userId;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public long id() {
        return id;
    }

    public String body() {
        return body;
    }

    public String userId() {
        return userId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
