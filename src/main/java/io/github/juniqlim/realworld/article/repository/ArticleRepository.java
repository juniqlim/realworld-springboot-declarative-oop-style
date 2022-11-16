package io.github.juniqlim.realworld.article.repository;

import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.user.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleRepository {
    private final List<Article> articles = new ArrayList<>();
    private final AtomicLong commentSequence = new AtomicLong(1);

    public void save(Article article) {
        articles.add(article);
    }

    public Article findBySlug(String slug) {
        return articles.stream()
            .filter(article -> article.equalsSlug(slug))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Article not found"));
    }

    public void update(String slug, Article article) {
        articles.removeIf(a -> a.equalsSlug(slug));
        articles.add(article);
    }

    public List<Article> findByTagAuthorIdFavoriteUserIdOrderByRegdate(String tag, User.Id authorId, User.Id favoriteUserId, int offset, int limit) {
        Conditional conditional = new Conditional(tag, authorId, favoriteUserId);
        return articles.stream()
            .sorted((article1, article2) -> article2.compareCreatedAt(article1))
            .filter(conditional::value)
            .skip((long) offset * limit)
            .limit(limit)
            .collect(Collectors.toList());
    }

    public void delete(String slug) {
        articles.removeIf(article -> article.equalsSlug(slug));
    }

    public long findCommentSequence() {
        return commentSequence.getAndIncrement();
    }

    public List<Article> findByUserIds(List<User.Id> followUsers, int offset, int limit) {
        return articles.stream()
            .sorted((article1, article2) -> article2.compareCreatedAt(article1))
            .filter(article -> followUsers.contains(article.authorId()))
            .skip((long) offset * limit)
            .limit(limit)
            .collect(Collectors.toList());
    }

    private static class Conditional {
        private final String tag;
        private final User.Id authorId;

        private final User.Id favoriteUserId;

        public Conditional(String tag, User.Id authorId, User.Id favoriteUserId) {
            this.tag = tag;
            this.authorId = authorId;
            this.favoriteUserId = favoriteUserId;
        }

        public boolean value(Article article) {
            if (tag != null && article.equalsTag(tag)) {
                return true;
            }
            if (authorId != null && article.equalsAuthorId(authorId)) {
                return true;
            }
            if (favoriteUserId != null && article.isFavorite(favoriteUserId)) {
                return true;
            }
            return false;
        }
    }
}
