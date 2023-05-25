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
        favoriteArticleJpaRepository.save(new FavoriteArticleEntity(
            new FavoriteArticleEntity.FavoriteArticleId(
                favorite.articleId().value(),
                favorite.favoriteUserId().value()
            )
        ));
    }

    @Override
    public List<Id> findArticleIdsByUserId(Id favoriteUserId) {
        return null;
    }

    @Override
    public void delete(FavoriteArticle unFavoriteArticle) {
        favoriteArticleJpaRepository.delete(new FavoriteArticleEntity(
            new FavoriteArticleEntity.FavoriteArticleId(
                unFavoriteArticle.articleId().value(),
                unFavoriteArticle.favoriteUserId().value()
            )
        ));
    }

    @Override
    public boolean isExist(Id articleId, Id favoriteUserId) {
        return favoriteArticleJpaRepository.findById(new FavoriteArticleEntity.FavoriteArticleId(
            articleId.value(),
            favoriteUserId.value()
        )).isPresent();
    }

    @Override
    public void deleteByArticleId(Id articleId) {
        favoriteArticleJpaRepository.deleteByIdArticleId(articleId.value());
    }
}