package io.github.juniqlim.realworld.article;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import autoparams.AutoSource;
import io.github.juniqlim.realworld.article.CreateArticle.Request;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.article.repository.TagRepository;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.repository.UserRepository.Collection;
import java.util.Arrays;
import org.junit.jupiter.params.ParameterizedTest;

class FavoriteArticleTest {
    @ParameterizedTest
    @AutoSource
    void test(User user) {
        Collection userRepository = new Collection();
        userRepository.save(user);
        ArticleRepository articleRepository = new ArticleRepository();
        Article article = new CreateArticle(articleRepository, new FindUser(userRepository), new TagUseCase(new TagRepository())).create(
            new Request("How to train your dragon", "Ever wonder how?", "You have to believe",
                user.token(), Arrays.asList("reactjs", "angularjs", "dragons")));

        Article favoritedArticle = new FavoriteArticle(articleRepository).favorite(article.slug(), new User.Id(2));
        assertTrue(favoritedArticle.isFavorite(new User.Id(2)));
        assertEquals(1, favoritedArticle.favoritesCount());

        favoritedArticle.unFavorite(new User.Id(2));
        assertFalse(favoritedArticle.isFavorite(new User.Id(2)));
        assertEquals(0, favoritedArticle.favoritesCount());
    }
}
