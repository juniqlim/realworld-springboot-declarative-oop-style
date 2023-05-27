package io.github.juniqlim.realworld.comment.repository.rdb;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
class CommentEntity {
    @Id
    private long id;
    private long articleId;
    private String body;
    private long commenterUserId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    protected CommentEntity() {
    }

    public CommentEntity(long id, long articleId, String body, long commenterUserId) {
        this.id = id;
        this.articleId = articleId;
        this.body = body;
        this.commenterUserId = commenterUserId;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public long id() {
        return id;
    }

    public long articleId() {
        return articleId;
    }

    public String body() {
        return body;
    }

    public long commenterUserId() {
        return commenterUserId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
