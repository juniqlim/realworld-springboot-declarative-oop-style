package io.github.juniqlim.realworld.article.repository.rdb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.juniqlim.realworld.Fixture;
import org.junit.jupiter.api.Test;

class ArticleToArticleEntityTest {
    @Test
    void test() {
        assertEquals(
            RDBFixture.JAKE_ARTICLE_ENTITY.getFavoriteUserIds(),
            new ArticleToArticleEntity().articleEntity(Fixture.JAKE_ARTICLE).getFavoriteUserIds()
        );
    }
}