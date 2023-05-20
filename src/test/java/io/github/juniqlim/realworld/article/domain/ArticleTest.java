package io.github.juniqlim.realworld.article.domain;

import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.juniqlim.realworld.Fixture;
import org.junit.jupiter.api.Test;

class ArticleTest {
    @Test
    void equalsAuthorId() {
        assertTrue(Fixture.JAKE_ARTICLE.equalsAuthorId(Fixture.LONG_ID_ONE));
    }

    @Test
    void isFavorite() {
        Article article = Fixture.JAKE_ARTICLE;

        article.favorite(Fixture.LONG_ID_ONE);

        assertTrue(article.isFavorite(Fixture.LONG_ID_ONE));
    }
}
