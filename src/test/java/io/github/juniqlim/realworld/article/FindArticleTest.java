package io.github.juniqlim.realworld.article;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.juniqlim.object.jwt.Jwt;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FindArticleTest {
    ArticleRepository articleRepository = new ArticleRepository();
    FindUser findUser;

    @BeforeEach
    void setUp() {
        articleRepository.save(new Article("How to train your dragon", "Ever wonder how?", "You have to believe",
            new User.Id(1), Arrays.asList("reactjs", "angularjs", "dragons")));

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
        article.favorite(new User.Id(1));
        articleRepository.update(article.slug(), article);

        assertEquals("How to train your dragon",
            new FindArticle(articleRepository, findUser).find(new FindArticle.Request(null, null, "Jacob", 0, 100))
                .get(0).title());
    }
}
