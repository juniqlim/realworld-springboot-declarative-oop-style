package io.github.juniqlim.realworld.article.repository;

import io.github.juniqlim.realworld.article.domain.Article;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Article> findByTagAuthorFavoritedOrderByRegdate(String tag, String author, String favorited, int offset, int limit) {
        Conditional conditional = new Conditional(tag, author, favorited);
        return articles.stream()
            .sorted((article1, article2) -> article2.compareCreatedAt(article1))
            .filter(conditional::value)
            .skip((long) offset * limit)
            .limit(limit)
            .collect(Collectors.toList());
    }

    private static class Conditional {
        private final String tag;
        private final String author;
        private final String favorited;

        public Conditional(String tag, String author, String favorited) {
            this.tag = tag;
            this.author = author;
            this.favorited = favorited;
        }

        public boolean value(Article article) {
            if (tag != null && article.equalsTag(tag)) {
                return true;
            }
            if (author != null && article.equalsAuthor(author)) {
                return true;
            }
            if (favorited != null && article.equalsFavorited(favorited)) {
                return true;
            }
            return false;
        }
    }
}
