package io.github.juniqlim.realworld.article.domain;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ArticleTest {
    @Test
    void equalsAuthorId() {
        Article article = new Article("How to train your dragon", "Ever wonder how?", "You have to believe",
                "idid", null);

        assertTrue(article.equalsAuthorId("idid"));
    }

    @Test
    void isFavorite() {
        Article article = new Article("How to train your dragon", "Ever wonder how?", "You have to believe",
            "idid", null);

        article.favorite("jakeUserId");

        assertTrue(article.isFavorite("jakeUserId"));
    }
}
