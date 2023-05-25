package io.github.juniqlim.realworld.favorite;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.favorite.repository.FavoriteArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FavoriteArticleUseCaseTest {
    private FavoriteArticleUseCase favoriteUseCase;

    @BeforeEach
    void setUp() {
        favoriteUseCase = new FavoriteArticleUseCase.FavoriteArticleService(new FavoriteArticleRepository.Fake());
    }

    @Test
    void testFavoriteArticle() {
        favoriteUseCase.favoriteArticle(Fixture.LONG_ID_ONE, Fixture.LONG_ID_ONE);
        favoriteUseCase.favoriteArticle(Fixture.LONG_ID_THREE, Fixture.LONG_ID_ONE);

        List<Id> articleIds = favoriteUseCase.findArticleIdsByFavoriteUserId(Fixture.LONG_ID_ONE);

        assertEquals(2, articleIds.size());
        assertEquals(Fixture.LONG_ID_ONE, articleIds.get(0));
        assertEquals(Fixture.LONG_ID_THREE, articleIds.get(1));
    }
}