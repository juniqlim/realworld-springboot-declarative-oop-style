package io.github.juniqlim.realworld.article.domain;

import io.github.juniqlim.realworld.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Article {
    private final Id id;
    private final Slug slug;
    private final String title;
    private final String description;
    private final String body;
    private final List<Tag> tags;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final List<Id> favoriteUserIds;
    private final Id authorId;

    public Article(Id id, String title, String description, String body, Id authorId, List<Tag> tags) {
        this(id, title, description, body, authorId, tags, new ArrayList<>());
    }

    private Article(Id id, String title, String description, String body, Id authorId, List<Tag> tags,
        List<Id> favoriteUserIds) {
        this(id, new Slug(title), title, description, body, tags, LocalDateTime.now(), LocalDateTime.now(),
            favoriteUserIds, authorId);
    }

    public Article(Id id, Slug slug, String title, String description, String body, List<Tag> tags, LocalDateTime createdAt,
        LocalDateTime updatedAt, List<Id> favoriteUserIds, Id authorId) {
        this.id = id;
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.body = body;
        this.tags = tags;
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

    public int favoritesCount() {
        return favoriteUserIds.size();
    }

    public boolean equalsSlug(String slug) {
        return this.slug.equalsString(slug);
    }

    public Article updateTitle(String title) {
        return new Article(id, title, description, body, authorId, tags, favoriteUserIds);
    }

    public Article updateDescription(String description) {
        return new Article(id, title, description, body, authorId, tags, favoriteUserIds);
    }

    public Article updateBody(String body) {
        return new Article(id, title, description, body, authorId, tags, favoriteUserIds);
    }

    public boolean equalsTag(String tag) {
        return tags.contains(new Tag(tag));
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

    public void favorite(Id userId) {
        favoriteUserIds.add(userId);
    }

    public void unFavorite(Id userId) {
        favoriteUserIds.remove(userId);
    }

    public boolean isFavorite(Id userId) {
        return favoriteUserIds.contains(userId);
    }

    public Id authorId() {
        return authorId;
    }

    public List<String> tagList() {
        return tags.stream()
            .map(Tag::value)
            .collect(Collectors.toList());
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }

    public boolean favorited(Id userId) {
        return favoriteUserIds.contains(userId);
    }
}
