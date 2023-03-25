package io.github.juniqlim.realworld.comment.web;

import io.github.juniqlim.realworld.article.domain.Comment;
import io.github.juniqlim.realworld.article.web.Author;
import io.github.juniqlim.realworld.user.domain.Profile;
import java.time.LocalDateTime;

public class CommentResponse {
    private long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String body;
    private Author author;

    public CommentResponse(Comment comment, Profile profile) {
        this(comment.id(), comment.createdAt(), comment.updatedAt(), comment.body(), new Author(profile));
    }

    CommentResponse(long id, LocalDateTime createdAt, LocalDateTime updatedAt, String body, Author author) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.body = body;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getBody() {
        return body;
    }

    public Author getAuthor() {
        return author;
    }
}