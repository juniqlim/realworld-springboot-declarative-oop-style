package io.github.juniqlim.realworld.article.repository.rdb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.juniqlim.realworld.Fixture;
import org.junit.jupiter.api.Test;

class ArticleEntityToArticleTest {
    @Test
    void test() {
        assertEquals(
            Fixture.JAKE_ARTICLE.favoriteUserIds().get(1),
            new ArticleEntityToArticle().article(RDBFixture.JAKE_ARTICLE_ENTITY).favoriteUserIds().get(1)
        );
    }
}