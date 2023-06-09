package io.github.juniqlim.realworld.article.domain;

import io.github.juniqlim.realworld.Id;

import java.time.LocalDateTime;
import java.util.Objects;

public class Article {
    private final Id id;
    private final String slug;
    private final String title;
    private final String description;
    private final String body;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final int favoritesCount;
    private final Id authorId;

    public Article(Id id, String title, String description, String body, Id authorId) {
        this(id, title, description, body, authorId, 0);
    }

    private Article(Id id, String title, String description, String body, Id authorId,
                   int favoritesCount) {
        this(id, new Slugify().withDash(title), title, description, body, LocalDateTime.now(), LocalDateTime.now(),
            favoritesCount, authorId);
    }

    public Article(Id id, String slug, String title, String description, String body, LocalDateTime createdAt,
                   LocalDateTime updatedAt, int favoritesCount, Id authorId) {
        this.id = id;
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.body = body;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.favoritesCount = favoritesCount;
        this.authorId = authorId;
    }

    public Id id() {
        return id;
    }

    public String slug() {
        return slug;
    }

    public String title() {
        return title;
    }

    public String description() {
        return description;
    }

    public String body() {
        return body;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public int favoritesCount() {
        return favoritesCount;
    }

    public boolean equalsSlug(String slug) {
        return this.slug.equals(slug);
    }

    public Article updateTitle(String title) {
        return new Article(id, title, description, body, authorId, favoritesCount);
    }

    public Article updateDescription(String description) {
        return new Article(id, title, description, body, authorId, favoritesCount);
    }

    public Article updateBody(String body) {
        return new Article(id, title, description, body, authorId, favoritesCount);
    }

    public boolean equalsAuthorId(Id authorId) {
        return this.authorId.equals(authorId);
    }

    public boolean equalsSlugAndAuthorId(String slug, Id authorId) {
        return equalsSlug(slug) && equalsAuthorId(authorId);
    }

    public int compareCreatedAt(Article article) {
        return createdAt.compareTo(article.createdAt());
    }

    public Id authorId() {
        return authorId;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id) && Objects.equals(slug, article.slug) && Objects.equals(title, article.title) && Objects.equals(description, article.description) && Objects.equals(body, article.body) && Objects.equals(createdAt, article.createdAt) && Objects.equals(updatedAt, article.updatedAt) && Objects.equals(favoritesCount, article.favoritesCount) && Objects.equals(authorId, article.authorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, slug, title, description, body, createdAt, updatedAt, favoritesCount, authorId);
    }

    public Article increseFavoritesCount() {
        return new Article(id, slug, title, description, body, createdAt, updatedAt, favoritesCount + 1, authorId);
    }

    public Article decreaseFavoritesCount() {
        return new Article(id, slug, title, description, body, createdAt, updatedAt,
            favoritesCount > 0 ? favoritesCount - 1 : 0,
            authorId);
    }
}
