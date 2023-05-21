package io.github.juniqlim.realworld.article;

import autoparams.AutoSource;
import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.article.CreateArticle.Request;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleArrayListRepository;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.repository.UserRepository.Collection;
import org.junit.jupiter.params.ParameterizedTest;

import static org.junit.jupiter.api.Assertions.*;

class FavoriteArticleTest {
    @ParameterizedTest
    @AutoSource
    void test(User user) {
        Collection userRepository = new Collection();
        userRepository.save(user);
        ArticleRepository articleRepository = new ArticleArrayListRepository();
        Article article = new CreateArticle(articleRepository, new FindUser(userRepository))
            .create(
                new Request("How to train your dragon", "Ever wonder how?", "You have to believe",
                user.token())
            );

        Article favoritedArticle = new FavoriteArticle(articleRepository).favorite(article.slug(), Fixture.LONG_ID_TWO);
        assertTrue(favoritedArticle.isFavorite(Fixture.LONG_ID_TWO));
        assertEquals(1, favoritedArticle.favoritesCount());

        Article unFavoritedArticle = favoritedArticle.unFavorite(Fixture.LONG_ID_TWO);
        assertFalse(unFavoritedArticle.isFavorite(Fixture.LONG_ID_TWO));
        assertEquals(0, unFavoritedArticle.favoritesCount());
    }
}
