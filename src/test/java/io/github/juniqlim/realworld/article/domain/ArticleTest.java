package io.github.juniqlim.realworld.article.domain;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ArticleTest {
    @Test
    void test() {
        Article article = new Article("How to train your dragon", "Ever wonder how?", "You have to believe",
                "idid", null);

        assertTrue(article.equalsAuthorId("idid"));
    }
}
