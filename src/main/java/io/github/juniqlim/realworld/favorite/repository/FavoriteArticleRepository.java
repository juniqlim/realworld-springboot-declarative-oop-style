package io.github.juniqlim.realworld.favorite.repository;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.favorite.domain.FavoriteArticle;

import java.util.Arrays;
import java.util.List;

public interface FavoriteArticleRepository {
    void save(FavoriteArticle favorite);

    List<Id> findArticleIdsByUserId(Id favoriteUserId);

    void delete(FavoriteArticle unFavoriteArticle);

    boolean isExist(Id articleId, Id favoriteUserId);

    void deleteByArticleId(Id articleId);

    class Fake implements FavoriteArticleRepository {

        @Override
        public void save(FavoriteArticle favorite) {

        }

        @Override
        public List<Id> findArticleIdsByUserId(Id favoriteUserId) {
            return Arrays.asList(new Id.LongId(1), new Id.LongId(3));
        }

        @Override
        public void delete(FavoriteArticle unFavoriteArticle) {

        }

        @Override
        public boolean isExist(Id articleId, Id favoriteUserId) {
            return true;
        }

        @Override
        public void deleteByArticleId(Id articleId) {

        }
    }
}
