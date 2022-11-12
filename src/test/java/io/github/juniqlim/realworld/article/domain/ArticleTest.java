package io.github.juniqlim.realworld.article.domain;

import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.juniqlim.realworld.user.domain.User;
import org.junit.jupiter.api.Test;

class ArticleTest {
    @Test
    void equalsAuthorId() {
        Article article = new Article("How to train your dragon", "Ever wonder how?", "You have to believe",
                new User.Id(1), null);

        assertTrue(article.equalsAuthorId(new User.Id(1)));
    }

    @Test
    void isFavorite() {
        Article article = new Article("How to train your dragon", "Ever wonder how?", "You have to believe",
            new User.Id(1), null);

        article.favorite(new User.Id(1));

        assertTrue(article.isFavorite(new User.Id(1)));
    }
}
