package io.github.juniqlim.realworld.article.repository;

import io.github.juniqlim.realworld.article.domain.Article;
import java.util.ArrayList;
import java.util.List;

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
}
