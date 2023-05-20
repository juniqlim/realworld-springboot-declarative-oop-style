package io.github.juniqlim.realworld.article.repository.rdb;

import io.github.juniqlim.realworld.article.domain.Article;

public class ArticleToArticleEntity {
    public ArticleToArticleEntity() {
    }

    ArticleEntity articleEntity(Article a) {
        return new ArticleEntity(a.id().value(), a.slug(), a.title(), a.description(), a.body(), a.createdAt(), a.updatedAt(),
            a.authorId().value());
    }
}