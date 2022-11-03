package io.github.juniqlim.realworld.article.repository;

import io.github.juniqlim.realworld.article.domain.Article;
import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    private final List<Article> articles = new ArrayList<>();

    public void save(Article article) {
        articles.add(article);
    }
}
