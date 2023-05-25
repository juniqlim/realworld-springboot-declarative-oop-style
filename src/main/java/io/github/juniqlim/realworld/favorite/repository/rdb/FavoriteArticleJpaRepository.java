package io.github.juniqlim.realworld.favorite.repository.rdb;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface FavoriteArticleJpaRepository extends JpaRepository<FavoriteArticleEntity, FavoriteArticleEntity.FavoriteArticleId> {
    List<FavoriteArticleEntity> findByIdFavoriteUserId(long favoriteUserId);
    void deleteByIdArticleId(long articleId);
}
