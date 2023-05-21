package io.github.juniqlim.realworld.article.domain;

import io.github.juniqlim.realworld.Fixture;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ArticleTest {
    @Test
    void equalsAuthorId() {
        assertTrue(Fixture.JAKE_ARTICLE.equalsAuthorId(Fixture.LONG_ID_ONE));
    }

    @Test
    void isFavorite() {
        Article article = Fixture.JAKE_ARTICLE;

        Article favoritedArticle = article.favorite(Fixture.LONG_ID_ONE);

        assertTrue(favoritedArticle.isFavorite(Fixture.LONG_ID_ONE));
    }
}
