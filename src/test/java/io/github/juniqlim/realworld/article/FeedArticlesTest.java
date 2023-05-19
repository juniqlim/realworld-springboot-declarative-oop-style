package io.github.juniqlim.realworld.article;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.TestPublicKey;
import io.github.juniqlim.realworld.article.FeedArticles.Request;
import io.github.juniqlim.realworld.article.web.ArticleResponse;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.FollowUser;
import io.github.juniqlim.realworld.user.User.UserByToken;
import io.github.juniqlim.realworld.user.web.Token;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import org.junit.jupiter.api.Test;

class FeedArticlesTest {
    @Test
    void test() throws InvalidKeySpecException, NoSuchAlgorithmException {
        new FollowUser(Fixture.USER_REPOSITORY).follow(Fixture.JUNIQ.token(), Fixture.JAKE.username());
        new FollowUser(Fixture.USER_REPOSITORY).follow(Fixture.JUNIQ.token(), Fixture.MINK.username());

        io.github.juniqlim.realworld.user.User loginUser = new UserByToken(new FindUser(Fixture.USER_REPOSITORY),
            new Token.Jws(new TestPublicKey().publicKey(), "token " + Fixture.JUNIQ.token()));
        List<ArticleResponse> articles = new FeedArticles(Fixture.ARTICLE_REPOSITORY, new FindUser(Fixture.USER_REPOSITORY)).articles(
            new Request(loginUser, 0, 1));

        assertEquals(1, articles.size());
    }
}
