package io.github.juniqlim.realworld.article.repository;

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

    public void update(String slug, Article article) {
        articles.removeIf(a -> a.equalsSlug(slug));
        articles.add(article);
    }

    public List<Article> findByTagAuthorIdFavoriteUserIdOrderByRegdate(String tag, String authorId, String favoriteUserId, int offset, int limit) {
        Conditional conditional = new Conditional(tag, authorId, favoriteUserId);
        return articles.stream()
            .sorted((article1, article2) -> article2.compareCreatedAt(article1))
            .filter(conditional::value)
            .skip((long) offset * limit)
            .limit(limit)
            .collect(Collectors.toList());
    }

    private static class Conditional {
        private final String tag;
        private final String authorId;

        private final String favoriteUserId;

        public Conditional(String tag, String authorId, String favoriteUserId) {
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
