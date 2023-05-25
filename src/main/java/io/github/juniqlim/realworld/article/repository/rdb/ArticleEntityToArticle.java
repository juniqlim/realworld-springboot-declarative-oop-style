package io.github.juniqlim.realworld.article.repository.rdb;

import io.github.juniqlim.realworld.Id.LongId;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.domain.Slug;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleEntityToArticle {
    public ArticleEntityToArticle() {
    }

    Article article(ArticleEntity e) {
        return new Article(new LongId(e.getId()), new Slug(e.getSlug()), e.getTitle(), e.getDescription(), e.getBody(),
            e.getCreatedAt(), e.getUpdatedAt(),
            e.getFavoritesCount(), new LongId(e.getAuthorUserId()));
    }

    List<Article> articles(List<ArticleEntity> es) {
        return es.stream().map(this::article).collect(Collectors.toList());
    }
}