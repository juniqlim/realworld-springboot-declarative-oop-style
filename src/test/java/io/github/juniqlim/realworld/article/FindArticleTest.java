package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.article.FindArticle.Request;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleArrayListRepository;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindArticleTest {
    ArticleRepository articleRepository = new ArticleArrayListRepository();
    FindUser findUser;

    @BeforeEach
    void setUp() {
        articleRepository.save(Fixture.JAKE_ARTICLE);

        UserRepository userRepository = new UserRepository.Collection();
        userRepository.save(Fixture.JAKE);
        findUser = new FindUser(userRepository);
    }

    @Test
    void test() {
        assertEquals("How to train your dragon",
            new FindArticle(articleRepository, findUser).find("how-to-train-your-dragon").title());
    }

    @Test
    void findByAuthorName() {
        assertEquals("How to train your dragon",
            new FindArticle(articleRepository, findUser).find(new FindArticle.Request(null, "Jacob", null, 0, 100))
                .get(0).title());
    }

    @Test
    void findByFavoriteUserName() {
        Article favoritedArticle = articleRepository.findBySlug("how-to-train-your-dragon").favorite(Fixture.JAKE.id());
        articleRepository.update(favoritedArticle.slug(), favoritedArticle);

        assertEquals("How to train your dragon",
            new FindArticle(articleRepository, findUser).find(
                    new Request(null, null, Fixture.JAKE.username(), 0, 100))
                .get(0).title());
    }
}
