package io.github.juniqlim.realworld.article.domain;

import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.domain.User.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Article {
    private final Slug slug;
    private final String title;
    private final String description;
    private final String body;
    private final List<Tag> tags;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final List<User.Id> favoriteUserIds;
    private final User.Id authorId;

    private final List<Comment> comments;

    public Article(String title, String description, String body, User.Id authorId, List<Tag> tags) {
        this(title, description, body, authorId, tags, new ArrayList<>(), new ArrayList<>());
    }

    public Article(String title, String description, String body, User.Id authorId, List<Tag> tags,
        List<User.Id> favoriteUserIds, List<Comment> comments) {
        this.slug = new Slug(title);
        this.title = title;
        this.description = description;
        this.body = body;
        this.tags = tags;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.favoriteUserIds = favoriteUserIds;
        this.authorId = authorId;
        this.comments = comments;
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

    public List<Comment> comments() {
        return comments;
    }

    public boolean equalsSlug(String slug) {
        return this.slug.equalsString(slug);
    }

    public Article updateTitle(String title) {
        return new Article(title, description, body, authorId, tags, favoriteUserIds, comments);
    }

    public Article updateDescription(String description) {
        return new Article(title, description, body, authorId, tags, favoriteUserIds, comments);
    }

    public Article updateBody(String body) {
        return new Article(title, description, body, authorId, tags, favoriteUserIds, comments);
    }

    public boolean equalsTag(String tag) {
        return tags.contains(new Tag(tag));
    }

    public boolean equalsAuthorId(User.Id authorId) {
        return this.authorId.equals(authorId);
    }

    public int compareCreatedAt(Article article) {
        return createdAt.compareTo(article.createdAt());
    }

    public void favorite(User.Id userId) {
        favoriteUserIds.add(userId);
    }

    public void unFavorite(User.Id userId) {
        favoriteUserIds.remove(userId);
    }

    public boolean isFavorite(User.Id userId) {
        return favoriteUserIds.contains(userId);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void deleteComment(long commentId, User.Id userId) {
        comments.stream()
            .filter(comment -> comment.id() == commentId)
            .filter(comment -> comment.userId().equals(userId))
            .findFirst()
            .ifPresent(comments::remove);
    }

    public User.Id authorId() {
        return authorId;
    }

    public List<String> tagList() {
        return tags.stream()
            .map(tag -> tag.value())
            .collect(Collectors.toList());
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }

    public boolean favorited(Id userId) {
        return favoriteUserIds.contains(userId);
    }

    static class Author {
        private String username;
        private String bio;
        private String image;
        private boolean following;

        public String username() {
            return username;
        }
    }
}
