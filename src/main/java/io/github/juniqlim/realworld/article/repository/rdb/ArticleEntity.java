package io.github.juniqlim.realworld.article.repository.rdb;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "articles")
@SequenceGenerator(
    name = "SEQ_GENERATOR",
    sequenceName = "articleSequence",
    allocationSize = 1
)
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GENERATOR")
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
    private long authorUserId;

    protected ArticleEntity() {
    }

    ArticleEntity(Long id, String slug, String title, String description, String body, LocalDateTime createdAt,
        LocalDateTime updatedAt, long authorUserId) {
        this.id = id;
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.body = body;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    long getAuthorUserId() {
        return authorUserId;
    }
}
