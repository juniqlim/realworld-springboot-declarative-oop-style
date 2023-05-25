package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleArrayListRepository;
import io.github.juniqlim.realworld.favorite.FavoriteArticleUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FavoriteArticleTest {
    ArticleArrayListRepository articleRepository = new ArticleArrayListRepository();
    FavoriteArticle favoriteArticle;

    @BeforeEach
    void setup() {
        articleRepository.save(Fixture.JAKE_ARTICLE);
        favoriteArticle = new FavoriteArticle(articleRepository, new FavoriteArticleUseCase.Fake());
    }

    @Test
    void favorite() {
        Article favoritedArticle = favoriteArticle.favorite(Fixture.JAKE_ARTICLE.slug(), Fixture.LONG_ID_TWO);
        assertEquals(Fixture.JAKE_ARTICLE.favoritesCount() + 1, favoritedArticle.favoritesCount());
    }

    @Test
    void unFavorite() {
        Article unFavoritedArticle = favoriteArticle.unFavorite(Fixture.JAKE_ARTICLE.slug(), Fixture.LONG_ID_TWO);
        assertEquals(Fixture.JAKE_ARTICLE.favoritesCount() - 1, unFavoritedArticle.favoritesCount());
    }
}
