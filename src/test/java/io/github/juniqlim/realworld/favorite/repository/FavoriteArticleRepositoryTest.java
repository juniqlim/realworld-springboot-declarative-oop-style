package io.github.juniqlim.realworld.favorite.repository;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.favorite.domain.FavoriteArticle;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FavoriteArticleRepositoryTest {
    @Test
    void save() {
        assertDoesNotThrow(() -> new FavoriteArticleRepository.Fake().save(new FavoriteArticle(Fixture.LONG_ID_ONE, Fixture.LONG_ID_ONE)));
    }

    @Test
    void findArticleIdsByUserId() {
        assertEquals(Arrays.asList(new Id.LongId(1), new Id.LongId(3)), new FavoriteArticleRepository.Fake().findArticleIdsByUserId());
    }
}