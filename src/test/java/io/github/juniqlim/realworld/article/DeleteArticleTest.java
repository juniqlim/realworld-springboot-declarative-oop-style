package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.favorite.FavoriteArticleUseCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

class DeleteArticleTest {
    @Test
    void test() {
        new DeleteArticle(Fixture.ARTICLE_REPOSITORY, new FavoriteArticleUseCase.Fake()).delete(Fixture.JAKE_ARTICLE.slug(),
            Fixture.JAKE_ARTICLE.authorId());
        assertThrowsExactly(RuntimeException.class, () -> Fixture.ARTICLE_REPOSITORY.findBySlug(Fixture.JAKE_ARTICLE.slug()),
            "Article not found");
    }
}
