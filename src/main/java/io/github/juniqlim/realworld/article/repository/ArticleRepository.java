package io.github.juniqlim.realworld.article.repository;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.article.domain.Article;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleRepository {
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

    public Article findBySlugAndUserId(String slug, Id userId) {
        return articles.stream()
            .filter(article -> article.equalsSlugAndAuthorId(slug, userId))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Article not found"));
    }

    public void update(String slug, Article article) {
        articles.removeIf(a -> a.equalsSlug(slug));
        articles.add(article);
    }

    public List<Article> findByTagAuthorIdFavoriteUserIdOrderByRegdate(String tag, Id authorId, Id favoriteUserId, int offset, int limit) {
        Conditional conditional = new Conditional(tag, authorId, favoriteUserId);
        return articles.stream()
            .sorted((article1, article2) -> article2.compareCreatedAt(article1))
            .filter(conditional::value)
            .skip((long) offset * limit)
            .limit(limit)
            .collect(Collectors.toList());
    }

    public List<Article> findByTagAuthorFavoriteUserOrderByRegdate(String tag,
        io.github.juniqlim.realworld.user.User author, io.github.juniqlim.realworld.user.User favoriteUser, int offset,
        int limit) {
        Conditional2 conditional = new Conditional2(tag, author, favoriteUser);
        return articles.stream()
            .sorted((article1, article2) -> article2.compareCreatedAt(article1))
            .filter(conditional::value)
            .skip((long) offset * limit)
            .limit(limit)
            .collect(Collectors.toList());
    }

    public void delete(String slug, Id userId) {
        articles.remove(findBySlugAndUserId(slug, userId));
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
            if (authorId == null || article.equalsAuthorId(authorId)) {
                isEqualsAuthorId = true;
            }
            if (favoriteUserId == null || article.isFavorite(favoriteUserId)) {
                isEqualsFavoriteUserId = true;
            }
            return isEqualsTag && isEqualsAuthorId && isEqualsFavoriteUserId;
        }
    }

    static class Conditional2 {
        private final String tag;
        private final io.github.juniqlim.realworld.user.User author;
        private final io.github.juniqlim.realworld.user.User favoriteUser;

        public Conditional2(String tag, io.github.juniqlim.realworld.user.User author, io.github.juniqlim.realworld.user.User favoriteUser) {
            this.tag = tag;
            this.author = author;
            this.favoriteUser = favoriteUser;
        }

        public boolean value(Article article) {
            boolean isEqualsTag = false, isEqualsAuthorId = false, isEqualsFavoriteUserId = false;
            if (tag == null || article.equalsTag(tag)) {
                isEqualsTag = true;
            }
            if (author.isNull() || article.equalsAuthorId(author.id())) {
                isEqualsAuthorId = true;
            }
            if (favoriteUser.isNull() || article.isFavorite(favoriteUser.id())) {
                isEqualsFavoriteUserId = true;
            }
            return isEqualsTag && isEqualsAuthorId && isEqualsFavoriteUserId;
        }
    }
}
