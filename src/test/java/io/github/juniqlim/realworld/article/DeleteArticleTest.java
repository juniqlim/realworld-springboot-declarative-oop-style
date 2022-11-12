package io.github.juniqlim.realworld.article;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import autoparams.AutoSource;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import org.junit.jupiter.params.ParameterizedTest;

class DeleteArticleTest {
    @ParameterizedTest
    @AutoSource
    void test(Article article) {
        ArticleRepository articleRepository = new ArticleRepository();
        articleRepository.save(article);

        new DeleteArticle(articleRepository).delete(article.slug());
        assertThrowsExactly(RuntimeException.class, () -> articleRepository.findBySlug(article.slug()),
            "Article not found");
    }
}
