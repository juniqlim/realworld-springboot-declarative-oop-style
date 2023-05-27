package io.github.juniqlim.realworld.article.repository.rdb;

import io.github.juniqlim.realworld.Fixture;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArticleToArticleEntityTest {
    @Test
    void articleEntity() {
        assertEquals(
            RDBFixture.JAKE_ARTICLE_ENTITY.getFavoritesCount(),
            new ArticleToArticleEntity().articleEntity(Fixture.JAKE_ARTICLE).getFavoritesCount()
        );
    }

    @Test
    void article() {
        assertEquals(
            Fixture.JAKE_ARTICLE.favoritesCount(),
            new ArticleToArticleEntity().article(RDBFixture.JAKE_ARTICLE_ENTITY).favoritesCount()
        );
    }
}