package io.github.juniqlim.realworld.favorite.repository;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.favorite.domain.FavoriteArticle;

import java.util.Arrays;
import java.util.List;

public interface FavoriteArticleRepository {
    void save(FavoriteArticle favorite);

    List<Id> findArticleIdsByUserId();

    class Fake implements FavoriteArticleRepository {

        @Override
        public void save(FavoriteArticle favorite) {

        }

        @Override
        public List<Id> findArticleIdsByUserId() {
            return Arrays.asList(new Id.LongId(1), new Id.LongId(3));
        }
    }
}
