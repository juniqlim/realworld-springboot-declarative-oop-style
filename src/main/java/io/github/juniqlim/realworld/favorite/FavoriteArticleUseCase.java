package io.github.juniqlim.realworld.favorite;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.favorite.domain.FavoriteArticle;
import io.github.juniqlim.realworld.favorite.repository.FavoriteArticleRepository;

import java.util.List;

class FavoriteArticleUseCase {
    private final FavoriteArticleRepository favoriteRepository;

    public FavoriteArticleUseCase(FavoriteArticleRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    public void favoriteArticle(Id articleId, Id favoriteUserId) {
        favoriteRepository.save(new FavoriteArticle(articleId, favoriteUserId));
    }

    public List<Id> findArticleIdsByFavoriteUserId(Id favoriteUserId) {
        return favoriteRepository.findArticleIdsByUserId();
    }
}
