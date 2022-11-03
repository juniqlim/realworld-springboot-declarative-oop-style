package io.github.juniqlim.realworld.article.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Article {
    private final Slug slug;
    private final String title;
    private final String description;
    private final String body;
    private final List<String> tagList;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final boolean favorited;
    private final int favoritesCount;
    private final Author author;

    public Article(String title, String description, String body, List<String> tagList) {
        this(title, description, body, tagList, false, 0, null);
    }

    public Article(String title, String description, String body, List<String> tagList,
        boolean favorited, int favoritesCount, Author author) {
        this.slug = new Slug(title);
        this.title = title;
        this.description = description;
        this.body = body;
        this.tagList = tagList;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.favorited = favorited;
        this.favoritesCount = favoritesCount;
        this.author = author;
    }

    public String getSlug() {
        return slug.value();
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

    public LocalDateTime getCreatAt() {
        return createdAt;
    }

    public Author getAuthor() {
        return author;
    }

    public boolean equalsSlug(String slug) {
        return this.slug.equalsString(slug);
    }

    public Article updateTitle(String title) {
        return new Article(title, description, body, tagList, favorited, favoritesCount, author);
    }

    public Article updateDescription(String description) {
        return new Article(title, description, body, tagList, favorited, favoritesCount, author);
    }

    public Article updateBody(String body) {
        return new Article(title, description, body, tagList, favorited, favoritesCount, author);
    }

    public boolean equalsTag(String tag) {
        return tagList.contains(tag);
    }

    public boolean equalsAuthor(String author) {
        return this.author.getUsername().equals(author);
    }

    public boolean equalsFavorited(String favorited) {
        return this.author.getUsername().equals(favorited);
    }

    public int compareCreatedAt(Article article) {
        return createdAt.compareTo(article.getCreatedAt());
    }

    static class Author {
        private String username;
        private String bio;
        private String image;
        private boolean following;

        public String getUsername() {
            return username;
        }

        public String getBio() {
            return bio;
        }

        public String getImage() {
            return image;
        }

        public boolean isFollowing() {
            return following;
        }
    }
}
