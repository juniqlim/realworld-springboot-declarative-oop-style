package io.github.juniqlim.realworld.article;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.article.FeedArticles.Request;
import io.github.juniqlim.realworld.article.web.ArticleResponse;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.FollowUser;
import java.util.List;
import org.junit.jupiter.api.Test;

class FeedArticlesTest {
    @Test
    void test() {
        new FollowUser(Fixture.USER_REPOSITORY).follow(Fixture.JUNIQ.token(), Fixture.JAKE.username());
        new FollowUser(Fixture.USER_REPOSITORY).follow(Fixture.JUNIQ.token(), Fixture.MINK.username());

        List<ArticleResponse> articles = new FeedArticles(Fixture.ARTICLE_REPOSITORY, new FindUser(Fixture.USER_REPOSITORY)).articles(
            new Request(Fixture.JUNIQ.id(), 0, 1));

        assertEquals(1, articles.size());
    }
}
