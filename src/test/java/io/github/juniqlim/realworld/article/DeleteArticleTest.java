package io.github.juniqlim.realworld.article;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import io.github.juniqlim.realworld.Fixture;
import org.junit.jupiter.api.Test;

class DeleteArticleTest {
    @Test
    void test() {
        new DeleteArticle(Fixture.ARTICLE_REPOSITORY).delete(Fixture.JAKE_ARTICLE.slug(),
            Fixture.JAKE_ARTICLE.authorId());
        assertThrowsExactly(RuntimeException.class, () -> Fixture.ARTICLE_REPOSITORY.findBySlug(Fixture.JAKE_ARTICLE.slug()),
            "Article not found");
    }
}
