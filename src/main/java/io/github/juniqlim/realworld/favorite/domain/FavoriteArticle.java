package io.github.juniqlim.realworld.favorite.domain;

import io.github.juniqlim.realworld.Id;

public class FavoriteArticle {
    private final Id articleId;
    private final Id favoriteUserId;

    public FavoriteArticle(Id articleId, Id favoriteUserId) {
        this.articleId = articleId;
        this.favoriteUserId = favoriteUserId;
    }

    public Id articleId() {
        return articleId;
    }

    public Id favoriteUserId() {
        return favoriteUserId;
    }
}
