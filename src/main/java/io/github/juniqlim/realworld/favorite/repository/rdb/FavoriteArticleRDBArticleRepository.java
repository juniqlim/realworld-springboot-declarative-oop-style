package io.github.juniqlim.realworld.favorite.repository.rdb;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.favorite.domain.FavoriteArticle;
import io.github.juniqlim.realworld.favorite.repository.FavoriteArticleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FavoriteArticleRDBArticleRepository implements FavoriteArticleRepository {
    private final FavoriteArticleJpaRepository favoriteArticleJpaRepository;

    public FavoriteArticleRDBArticleRepository(FavoriteArticleJpaRepository favoriteArticleJpaRepository) {
        this.favoriteArticleJpaRepository = favoriteArticleJpaRepository;
    }

    @Override
    public void save(FavoriteArticle favorite) {

    }

    @Override
    public List<Id> findArticleIdsByUserId() {
        return null;
    }
}