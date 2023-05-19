package io.github.juniqlim.realworld.article.domain;

import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.juniqlim.realworld.Fixture;
import org.junit.jupiter.api.Test;

class ArticleTest {
    @Test
    void equalsAuthorId() {
        Article article = new Article("How to train your dragon", "Ever wonder how?", "You have to believe",
                Fixture.LONG_ID_ONE, null);

        assertTrue(article.equalsAuthorId(Fixture.LONG_ID_ONE));
    }

    @Test
    void isFavorite() {
        Article article = new Article("How to train your dragon", "Ever wonder how?", "You have to believe",
            Fixture.LONG_ID_ONE, null);

        article.favorite(Fixture.LONG_ID_ONE);

        assertTrue(article.isFavorite(Fixture.LONG_ID_ONE));
    }
}
