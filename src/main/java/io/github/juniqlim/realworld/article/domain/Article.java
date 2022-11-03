package io.github.juniqlim.realworld.article.domain;

import java.util.List;

public class Article {
    private final String slug;
    private final String title;
    private final String description;
    private final String body;
    private final List<String> tagList;
    private final String createdAt;
    private final String updatedAt;
    private final boolean favorited;
    private final int favoritesCount;
    private final Author author;

    public Article(String title, String description, String body, List<String> tagList) {
        this(null, title, description, body, tagList, null, null, false, 0, null);
    }

    public Article(String slug, String title, String description, String body, List<String> tagList, String createdAt,
        String updatedAt, boolean favorited, int favoritesCount, Author author) {
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

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
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
