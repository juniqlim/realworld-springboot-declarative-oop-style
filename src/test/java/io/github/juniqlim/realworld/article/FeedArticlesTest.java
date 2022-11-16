package io.github.juniqlim.realworld.article;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.juniqlim.realworld.TestRepository;
import io.github.juniqlim.realworld.article.FeedArticles.Request;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.FollowUser;
import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import io.github.juniqlim.realworld.user.repository.UserRepository.Collection;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import org.junit.jupiter.api.Test;

class FeedArticlesTest {
    @Test
    void test() throws InvalidKeySpecException, NoSuchAlgorithmException {
        TestRepository testRepository = new TestRepository(new Collection(),
            new ArticleRepository());
        ArticleRepository articleRepository = testRepository.articleRepository();
        UserRepository userRepository = testRepository.userRepository();

        User juniq = userRepository.findByUsername("juniq");
        User jake = userRepository.findByUsername("Jacob");
        User mink = userRepository.findByUsername("mink");
        new FollowUser(userRepository).follow(juniq.token(), jake.username());
        new FollowUser(userRepository).follow(juniq.token(), mink.username());

        List<Article> articles = new FeedArticles(articleRepository, new FindUser(userRepository)).articles(
            new Request(juniq.token(), 0, 1));

        assertEquals(1, articles.size());
    }
}
