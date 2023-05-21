package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.article.CreateArticle.Request;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleArrayListRepository;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.repository.UserRepository.Collection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FavoriteArticleTest {
    @Test
    void test() {
        Collection userRepository = new Collection();
        userRepository.save(Fixture.JAKE);
        ArticleRepository articleRepository = new ArticleArrayListRepository();

        Article article = new CreateArticle(articleRepository, new FindUser(userRepository))
            .create(
                new Request(Fixture.JAKE_ARTICLE.title(), Fixture.JAKE_ARTICLE.description(),
                    Fixture.JAKE_ARTICLE.body(), Fixture.JAKE.id())
            );

        Article favoritedArticle = new FavoriteArticle(articleRepository).favorite(article.slug(), Fixture.LONG_ID_TWO);
        assertTrue(favoritedArticle.isFavorite(Fixture.LONG_ID_TWO));
        assertEquals(1, favoritedArticle.favoritesCount());

        Article unFavoritedArticle = favoritedArticle.unFavorite(Fixture.LONG_ID_TWO);
        assertFalse(unFavoritedArticle.isFavorite(Fixture.LONG_ID_TWO));
        assertEquals(0, unFavoritedArticle.favoritesCount());
    }
}
