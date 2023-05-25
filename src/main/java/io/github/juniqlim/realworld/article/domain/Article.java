package io.github.juniqlim.realworld.article.domain;

import io.github.juniqlim.realworld.Id;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Article {
    private final Id id;
    private final Slug slug;
    private final String title;
    private final String description;
    private final String body;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final List<Id> favoriteUserIds;
    private final Id authorId;

    public Article(Id id, String title, String description, String body, Id authorId) {
        this(id, title, description, body, authorId, new ArrayList<>());
    }

    public Article(Id id, String title, String description, String body, Id authorId,
                   List<Id> favoriteUserIds) {
        this(id, new Slug(title), title, description, body, LocalDateTime.now(), LocalDateTime.now(),
            favoriteUserIds, authorId);
    }

    public Article(Id id, Slug slug, String title, String description, String body, LocalDateTime createdAt,
        LocalDateTime updatedAt, List<Id> favoriteUserIds, Id authorId) {
        this.id = id;
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.body = body;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.favoriteUserIds = favoriteUserIds;
        this.authorId = authorId;
    }

    public Id id() {
        return id;
    }

    public String slug() {
        return slug.value();
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

    public List<Id> favoriteUserIds() {
        return favoriteUserIds;
    }

    public int favoritesCount() {
        return favoriteUserIds.size();
    }

    public boolean equalsSlug(String slug) {
        return this.slug.equalsString(slug);
    }

    public Article updateTitle(String title) {
        return new Article(id, title, description, body, authorId, favoriteUserIds);
    }

    public Article updateDescription(String description) {
        return new Article(id, title, description, body, authorId, favoriteUserIds);
    }

    public Article updateBody(String body) {
        return new Article(id, title, description, body, authorId, favoriteUserIds);
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

    public Article favorite(Id userId) {
        List<Id> favoriteUserIds = new ArrayList<>(this.favoriteUserIds);
        favoriteUserIds.add(userId);
        return new Article(id, new Slug(title), title, description, body, createdAt, updatedAt, favoriteUserIds, authorId);
    }

    public Article unFavorite(Id userId) {
        List<Id> favoriteUserIds = new ArrayList<>(this.favoriteUserIds);
        favoriteUserIds.remove(userId);
        return new Article(id, new Slug(title), title, description, body, createdAt, updatedAt, favoriteUserIds, authorId);
    }

    public boolean isFavorite(Id userId) {
        return favoriteUserIds.contains(userId);
    }

    public Id authorId() {
        return authorId;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }

    public boolean favorited(Id userId) {
        return favoriteUserIds.contains(userId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id) && Objects.equals(slug, article.slug) && Objects.equals(title, article.title) && Objects.equals(description, article.description) && Objects.equals(body, article.body) && Objects.equals(createdAt, article.createdAt) && Objects.equals(updatedAt, article.updatedAt) && Objects.equals(favoriteUserIds, article.favoriteUserIds) && Objects.equals(authorId, article.authorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, slug, title, description, body, createdAt, updatedAt, favoriteUserIds, authorId);
    }
}
