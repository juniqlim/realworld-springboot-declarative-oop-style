package io.github.juniqlim.realworld.article;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import autoparams.AutoSource;
import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.user.web.Token;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

class DeleteArticleTest {
    @Test
    void test() {
        new DeleteArticle(Fixture.ARTICLE_REPOSITORY).delete(Fixture.JAKE_ARTICLE.slug(),
            Fixture.JAKE_ARTICLE.authorId());
        assertThrowsExactly(RuntimeException.class, () -> Fixture.ARTICLE_REPOSITORY.findBySlug(Fixture.JAKE_ARTICLE.slug()),
            "Article not found");
    }
}
