package io.github.juniqlim.realworld.article.web;

import io.github.juniqlim.realworld.article.FeedArticles;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.User;
import io.github.juniqlim.realworld.user.web.Token;
import java.security.PublicKey;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FindFeedArticlesController {
    private final FeedArticles feedArticles;
    private final FindUser findUser;
    private final PublicKey publicKey;

    FindFeedArticlesController(FeedArticles feedArticles, FindUser findUser, PublicKey publicKey) {
        this.feedArticles = feedArticles;
        this.findUser = findUser;
        this.publicKey = publicKey;
    }

    @GetMapping("/api/articles/feed")
    public Response articles(@RequestHeader(name = "Authorization", required = false) String token, Request request) {
        return new Response(feedArticles.articles(
            new FeedArticles.Request(new User.UserByToken(findUser, new Token.Jws(publicKey, token)), request.limit, request.offset))
        );
    }

    private static class Request {
        private Integer limit;
        private Integer offset;

        public Request() {
            this.limit = 20;
            this.offset = 0;
        }

        public Integer getLimit() {
            return limit;
        }

        public Integer getOffset() {
            return offset;
        }

        public void setLimit(Integer limit) {
            this.limit = limit;
        }

        public void setOffset(Integer offset) {
            this.offset = offset;
        }
    }

    private static class Response {
        private final List<ArticleResponse> articles;

        Response(List<ArticleResponse> articles) {
            this.articles = articles;
        }

        public List<ArticleResponse> getArticles() {
            return articles;
        }
    }
}
