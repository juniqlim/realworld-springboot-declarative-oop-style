package io.github.juniqlim.realworld.article.repository.rdb;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.article.domain.Article;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleToArticleEntity {
    public ArticleToArticleEntity() {
    }

    ArticleEntity articleEntity(Article a) {
        return new ArticleEntity(a.id().value(), a.slug(), a.title(), a.description(), a.body(), a.createdAt(), a.updatedAt(),
            favoriteUserIds(a.favoriteUserIds()),
            a.authorId().value());
    }

    public static String favoriteUserIds(List<Id> favoriteUserIds) {
        return favoriteUserIds.stream().map(Id::value).map(String::valueOf).collect(Collectors.joining(","));
    }
}