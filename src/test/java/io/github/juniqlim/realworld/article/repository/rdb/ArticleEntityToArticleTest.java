package io.github.juniqlim.realworld.article.repository.rdb;

import io.github.juniqlim.realworld.Fixture;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArticleEntityToArticleTest {
    @Test
    void test() {
        assertEquals(
            Fixture.JAKE_ARTICLE.favoritesCount(),
            new ArticleEntityToArticle().article(RDBFixture.JAKE_ARTICLE_ENTITY).favoritesCount()
        );
    }
}