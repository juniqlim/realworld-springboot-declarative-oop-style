package io.github.juniqlim.realworld.article.repository.rdb;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "articles")
public class ArticleEntity {
    @Id
    private Long id;

    @Column(nullable = false)
    private String slug;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private int favoritesCount;

    @Column(nullable = false)
    private long authorUserId;

    protected ArticleEntity() {
    }

    ArticleEntity(Long id, String slug, String title, String description, String body, LocalDateTime createdAt,
                  LocalDateTime updatedAt, int favoritesCount, long authorUserId) {
        this.id = id;
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.body = body;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.favoritesCount = favoritesCount;
        this.authorUserId = authorUserId;
    }

    Long getId() {
        return id;
    }

    String getSlug() {
        return slug;
    }

    String getTitle() {
        return title;
    }

    String getDescription() {
        return description;
    }

    String getBody() {
        return body;
    }

    LocalDateTime getCreatedAt() {
        return createdAt;
    }

    LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    int getFavoritesCount() {
        return favoritesCount;
    }

    long getAuthorUserId() {
        return authorUserId;
    }
}
