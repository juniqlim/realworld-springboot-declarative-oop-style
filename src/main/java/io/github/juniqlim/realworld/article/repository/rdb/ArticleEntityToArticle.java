package io.github.juniqlim.realworld.article.repository.rdb;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.Id.LongId;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.domain.Slug;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleEntityToArticle {
    public ArticleEntityToArticle() {
    }

    Article article(ArticleEntity e) {
        return new Article(new LongId(e.getId()), new Slug(e.getSlug()), e.getTitle(), e.getDescription(), e.getBody(),
            e.getCreatedAt(), e.getUpdatedAt(),
            favoriteUserIds(e.getFavoriteUserIds()), new LongId(e.getAuthorUserId()));
    }

    List<Article> articles(List<ArticleEntity> es) {
        return es.stream().map(this::article).collect(Collectors.toList());
    }

    private static List<Id> favoriteUserIds(String favoriteUserIds) {
        if (favoriteUserIds == null || favoriteUserIds.isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.stream(favoriteUserIds.split(","))
            .map(id -> new LongId(Long.parseLong(id)))
            .collect(Collectors.toList());
    }
}