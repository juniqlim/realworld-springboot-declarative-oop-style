package io.github.juniqlim.realworld.favorite;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.favorite.domain.FavoriteArticle;
import io.github.juniqlim.realworld.favorite.repository.FavoriteArticleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

public interface FavoriteArticleUseCase {
    boolean favoriteArticle(Id articleId, Id favoriteUserId);
    boolean unFavoriteArticle(Id articleId, Id unFavoriteUserId);
    List<Id> findArticleIdsByFavoriteUserId(Id favoriteUserId);
    void deleteArticle(Id articleId);
    boolean isExist(Id articleId, Id favoriteUserId);

    @Service
    class FavoriteArticleService implements FavoriteArticleUseCase {
        private final FavoriteArticleRepository favoriteRepository;

        public FavoriteArticleService(FavoriteArticleRepository favoriteRepository) {
            this.favoriteRepository = favoriteRepository;
        }

        public boolean favoriteArticle(Id articleId, Id favoriteUserId) {
            if (!favoriteRepository.isExist(articleId, favoriteUserId)) {
                favoriteRepository.save(new FavoriteArticle(articleId, favoriteUserId));
                return true;
            }
            return false;
        }

        public boolean unFavoriteArticle(Id articleId, Id unFavoriteUserId) {
            if (favoriteRepository.isExist(articleId, unFavoriteUserId)) {
                favoriteRepository.delete(new FavoriteArticle(articleId, unFavoriteUserId));
                return true;
            }
            return false;
        }

        public List<Id> findArticleIdsByFavoriteUserId(Id favoriteUserId) {
            return favoriteRepository.findArticleIdsByUserId(favoriteUserId);
        }

        @Override
        public void deleteArticle(Id articleId) {
            favoriteRepository.deleteByArticleId(articleId);
        }

        @Override
        public boolean isExist(Id articleId, Id favoriteUserId) {
            return favoriteRepository.isExist(articleId, favoriteUserId);
        }
    }

    class Fake implements FavoriteArticleUseCase {
        @Override
        public boolean favoriteArticle(Id articleId, Id favoriteUserId) {
            return true;
        }

        @Override
        public boolean unFavoriteArticle(Id articleId, Id unFavoriteUserId) {
            return true;
        }

        @Override
        public List<Id> findArticleIdsByFavoriteUserId(Id favoriteUserId) {
            return Arrays.asList(new Id.LongId(1), new Id.LongId(3));
        }

        @Override
        public void deleteArticle(Id articleId) {

        }

        @Override
        public boolean isExist(Id articleId, Id favoriteUserId) {
            return false;
        }
    }
}
