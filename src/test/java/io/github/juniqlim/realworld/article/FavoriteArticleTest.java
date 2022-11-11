package io.github.juniqlim.realworld.article;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import autoparams.AutoSource;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import org.junit.jupiter.params.ParameterizedTest;

class FavoriteArticleTest {
    @ParameterizedTest
    @AutoSource
    void test(Article article) {
        ArticleRepository articleRepository = new ArticleRepository();
        articleRepository.save(article);

        Article favoritedArticle = new FavoriteArticle(articleRepository).favorite(article.getSlug(), "jakeUserId");
        assertTrue(favoritedArticle.isFavorite("jakeUserId"));
        assertEquals(1, favoritedArticle.getFavoritesCount());

        favoritedArticle.unFavorite("jakeUserId");
        assertFalse(favoritedArticle.isFavorite("jakeUserId"));
        assertEquals(0, favoritedArticle.getFavoritesCount());
    }
}
