package io.github.juniqlim.realworld.article.repository.rdb;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.domain.Slugify;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleToArticleEntity {
    public ArticleToArticleEntity() {
    }

    ArticleEntity articleEntity(Article a) {
        return new ArticleEntity(a.id().value(), a.slug(), a.title(), a.description(), a.body(), a.createdAt(), a.updatedAt(),
            a.favoritesCount(),
            a.authorId().value());
    }

    Article article(ArticleEntity e) {
        return new Article(new Id.LongId(e.getId()), new Slugify().withDash(e.getSlug()), e.getTitle(), e.getDescription(), e.getBody(),
            e.getCreatedAt(), e.getUpdatedAt(),
            e.getFavoritesCount(), new Id.LongId(e.getAuthorUserId()));
    }

    List<Article> articles(List<ArticleEntity> es) {
        return es.stream().map(this::article).collect(Collectors.toList());
    }
}