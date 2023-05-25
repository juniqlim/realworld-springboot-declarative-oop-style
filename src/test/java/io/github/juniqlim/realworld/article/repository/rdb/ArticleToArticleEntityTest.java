package io.github.juniqlim.realworld.article.repository.rdb;

import io.github.juniqlim.realworld.Fixture;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArticleToArticleEntityTest {
    @Test
    void test() {
        assertEquals(
            RDBFixture.JAKE_ARTICLE_ENTITY.getFavoritesCount(),
            new ArticleToArticleEntity().articleEntity(Fixture.JAKE_ARTICLE).getFavoritesCount()
        );
    }
}