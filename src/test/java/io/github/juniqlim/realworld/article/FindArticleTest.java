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
    String userId = Jwt.FAKE.token();

    @BeforeEach
    void setUp() {
        articleRepository.save(new Article("How to train your dragon", "Ever wonder how?", "You have to believe",
            userId, Arrays.asList("reactjs", "angularjs", "dragons")));

        UserRepository userRepository = new UserRepository();
        userRepository.save(new User(userId, "123", "Jacob", "jake@jake.jake"));
        findUser = new FindUser(userRepository);
    }

    @Test
    void test() {
        assertEquals("How to train your dragon",
            new FindArticle(articleRepository, findUser).find("how-to-train-your-dragon").getTitle());
    }

    @Test
    void findByAuthorName() {
        assertEquals("How to train your dragon",
            new FindArticle(articleRepository, findUser).find(new FindArticle.Request(null, "Jacob", null, 0, 100))
                .get(0).getTitle());
    }

    @Test
    void findByFavoriteUserName() {
        Article article = articleRepository.findBySlug("how-to-train-your-dragon");
        article.favorite(userId);
        articleRepository.update(article.getSlug(), article);

        assertEquals("How to train your dragon",
            new FindArticle(articleRepository, findUser).find(new FindArticle.Request(null, null, "Jacob", 0, 100))
                .get(0).getTitle());
    }
}
