package io.github.juniqlim.realworld.article;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.juniqlim.object.jwt.Jwt;
import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.article.FindArticle.Request;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.domain.Tag;
import io.github.juniqlim.realworld.article.repository.ArticleArrayListRepository;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FindArticleTest {
    ArticleRepository articleRepository = new ArticleArrayListRepository();
    FindUser findUser;

    @BeforeEach
    void setUp() {
        articleRepository.save(new Article(Fixture.LONG_ID_ONE, "How to train your dragon", "Ever wonder how?", "You have to believe",
            Fixture.LONG_ID_ONE, Arrays.asList(new Tag("reactjs"), new Tag("angularjs"), new Tag("dragons"))));

        UserRepository userRepository = new UserRepository.Collection();
        userRepository.save(new User(1, Jwt.FAKE.token(), "123", "Jacob", "jake@jake.jake"));
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
        Article article = articleRepository.findBySlug("how-to-train-your-dragon");
        article.favorite(Fixture.LONG_ID_ONE);
        articleRepository.update(article.slug(), article);

        assertEquals("How to train your dragon",
            new FindArticle(articleRepository, findUser).find(
                    new Request(null, null, "Jacob", 0, 100))
                .get(0).title());
    }
}
