package io.github.juniqlim.realworld.article.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Article {
    private final Slug slug;
    private final String title;
    private final String description;
    private final String body;
    private final List<String> tagList;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final List<String> favoriteUserIds;
    private final String authorId;

    private final List<Comment> comments;

    public Article(String title, String description, String body, String authorId, List<String> tagList) {
        this(title, description, body, authorId, tagList, new ArrayList<>(), new ArrayList<>());
    }

    public Article(String title, String description, String body, String authorId, List<String> tagList,
        List<String> favoriteUserIds, List<Comment> comments) {
        this.slug = new Slug(title);
        this.title = title;
        this.description = description;
        this.body = body;
        this.tagList = tagList;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.favoriteUserIds = favoriteUserIds;
        this.authorId = authorId;
        this.comments = comments;
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

    public List<String> getFavoriteUserIds() {
        return favoriteUserIds;
    }

    public int getFavoritesCount() {
        return favoriteUserIds.size();
    }

    public LocalDateTime getCreatAt() {
        return createdAt;
    }

    public String getAuthorId() {
        return authorId;
    }

    public boolean equalsSlug(String slug) {
        return this.slug.equalsString(slug);
    }

    public Article updateTitle(String title) {
        return new Article(title, description, body, authorId, tagList, favoriteUserIds, comments);
    }

    public Article updateDescription(String description) {
        return new Article(title, description, body, authorId, tagList, favoriteUserIds, comments);
    }

    public Article updateBody(String body) {
        return new Article(title, description, body, authorId, tagList, favoriteUserIds, comments);
    }

    public boolean equalsTag(String tag) {
        return tagList.contains(tag);
    }

    public boolean equalsAuthorId(String authorId) {
        return this.authorId.equals(authorId);
    }

    public int compareCreatedAt(Article article) {
        return createdAt.compareTo(article.getCreatedAt());
    }

    public void favorite(String userId) {
        favoriteUserIds.add(userId);
    }

    public void unFavorite(String userId) {
        favoriteUserIds.remove(userId);
    }

    public boolean isFavorite(String userId) {
        return favoriteUserIds.contains(userId);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
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
