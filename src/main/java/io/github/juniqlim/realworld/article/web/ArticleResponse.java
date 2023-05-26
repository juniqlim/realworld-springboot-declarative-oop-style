package io.github.juniqlim.realworld.article.web;

import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.user.domain.Profile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ArticleResponse {
    private final String slug;
    private final String title;
    private final String description;
    private final String body;
    private final List<String> tagList;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final boolean favorited;
    private final int favoritesCount;
    private final Author author;

    public ArticleResponse(Article article, Profile profile) {
        this(article, new ArrayList<>(), profile, false);
    }

    public ArticleResponse(Article article, List<String> tags, Profile profile) {
        this(article.slug(), article.title(), article.description(), article.body(), tags,
            article.createdAt(), article.updatedAt(), false, article.favoritesCount(),
            new Author(profile));
    }

    public ArticleResponse(Article article, List<String> tags, Profile profile, boolean favorited) {
        this(article.slug(), article.title(), article.description(), article.body(), tags,
            article.createdAt(), article.updatedAt(), favorited, article.favoritesCount(),
            new Author(profile));
    }

    private ArticleResponse(String slug, String title, String description, String body, List<String> tagList,
        LocalDateTime createdAt, LocalDateTime updatedAt, boolean favorited, int favoritesCount, Author author) {
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.body = body;
        this.tagList = tagList;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.favorited = favorited;
        this.favoritesCount = favoritesCount;
        this.author = author;
    }

    public String getSlug() {
        return slug;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getBody() {
        return body;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public int getFavoritesCount() {
        return favoritesCount;
    }

    public Author getAuthor() {
        return author;
    }
}
