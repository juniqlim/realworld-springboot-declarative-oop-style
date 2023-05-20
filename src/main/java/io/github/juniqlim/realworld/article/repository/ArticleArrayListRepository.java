package io.github.juniqlim.realworld.article.repository;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.article.domain.Article;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleArrayListRepository implements ArticleRepository {
    private final List<Article> articles = new ArrayList<>();

    public void save(Article article) {
        articles.add(article);
    }

    public Article findBySlug(String slug) {
        return articles.stream()
            .filter(article -> article.equalsSlug(slug))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Article not found"));
    }

    public Article findBySlugAndAuthorUserId(String slug, Id userId) {
        return articles.stream()
            .filter(article -> article.equalsSlugAndAuthorId(slug, userId))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Article not found"));
    }

    public void update(String slug, Article article) {
        articles.removeIf(a -> a.equalsSlug(slug));
        articles.add(article);
    }

    public List<Article> findByTagAndAuthorUserIdAndFavoriteUserIdOrderByRegdate(String tag, Id authorId, Id favoriteUserId, int offset, int limit) {
        Conditional conditional = new Conditional(tag, authorId, favoriteUserId);
        return articles.stream()
            .sorted((article1, article2) -> article2.compareCreatedAt(article1))
            .filter(conditional::value)
            .skip((long) offset * limit)
            .limit(limit)
            .collect(Collectors.toList());
    }

    public void delete(String slug, Id userId) {
        articles.remove(findBySlugAndAuthorUserId(slug, userId));
    }

    public List<Article> findByUserIds(List<Id> followUsers, int offset, int limit) {
        return articles.stream()
            .sorted((article1, article2) -> article2.compareCreatedAt(article1))
            .filter(article -> followUsers.contains(article.authorId()))
            .skip((long) offset * limit)
            .limit(limit)
            .collect(Collectors.toList());
    }

    static class Conditional {
        private final String tag;
        private final Id authorId;
        private final Id favoriteUserId;

        public Conditional(String tag, Id authorId, Id favoriteUserId) {
            this.tag = tag;
            this.authorId = authorId;
            this.favoriteUserId = favoriteUserId;
        }

        public boolean value(Article article) {
            boolean isEqualsTag = false, isEqualsAuthorId = false, isEqualsFavoriteUserId = false;
            if (tag == null || article.equalsTag(tag)) {
                isEqualsTag = true;
            }
            if (authorId.isEmpty() || article.equalsAuthorId(authorId)) {
                isEqualsAuthorId = true;
            }
            if (favoriteUserId.isEmpty() || article.isFavorite(favoriteUserId)) {
                isEqualsFavoriteUserId = true;
            }
            return isEqualsTag && isEqualsAuthorId && isEqualsFavoriteUserId;
        }
    }
}