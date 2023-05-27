package io.github.juniqlim.realworld.favorite.repository.rdb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class FavoriteArticleJpaRepositoryIT {
    @Autowired
    FavoriteArticleJpaRepository favoriteArticleJpaRepository;

    @Test
    void test() {
        favoriteArticleJpaRepository.save(new FavoriteArticleEntity(new FavoriteArticleEntity.FavoriteArticleId(1, 1)));
        favoriteArticleJpaRepository.save(new FavoriteArticleEntity(new FavoriteArticleEntity.FavoriteArticleId(3, 1)));

        List<FavoriteArticleEntity> favoriteArticleEntities = favoriteArticleJpaRepository.findByIdFavoriteUserId(1);
        List<Long> articleIds = favoriteArticleEntities.stream()
            .map(FavoriteArticleEntity::id)
            .map(FavoriteArticleEntity.FavoriteArticleId::articleId)
            .collect(Collectors.toList());
        assertEquals(Arrays.asList(1L, 3L), articleIds);
    }
}