package io.github.juniqlim.realworld.article.web;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.article.FindArticleResponse;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.web.Token.Jws;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.security.PublicKey;
import java.util.List;

@RestController
public class FindArticlesController {
    private final FindArticleResponse findArticleResponse;
    private final FindUser findUser;
    private final PublicKey publicKey;

    FindArticlesController(FindArticleResponse findArticleResponse, FindUser findUser, PublicKey publicKey) {
        this.findArticleResponse = findArticleResponse;
        this.findUser = findUser;
        this.publicKey = publicKey;
    }

    @GetMapping("/api/articles")
    public Response articles(@RequestHeader(name = "Authorization", required = false) String token, Request request) {
        return new Response(findArticleResponse.find(
            new FindArticleResponse.Request.Builder()
                .loginUserId(loginUserId(token))
                .tag(request.tag)
                .authorUserId(username(request.author))
                .favoriteUserId(username(request.favorited))
                .limit(request.limit)
                .offset(request.offset)
                .build())
        );
    }

    private Id username(String name) {
        if (name == null || name.isEmpty()) {
            return new Id.EmptyId();
        }
        return findUser.findIdByUsername(name);
    }

    private Id loginUserId(String token) {
        if (token == null || token.isEmpty()) {
            return new Id.EmptyId();
        }
        return findUser.findIdByToken(new Jws(publicKey, token));
    }

    private static class Request {
        private String tag;
        private String author;
        private String favorited;
        private Integer limit;
        private Integer offset;

        public Request(String tag, String author, String favorited) {
            this.tag = tag;
            this.author = author;
            this.favorited = favorited;
            this.limit = 20;
            this.offset = 0;
        }

        public String getTag() {
            return tag;
        }

        public String getAuthor() {
            return author;
        }

        public String getFavorited() {
            return favorited;
        }

        public Integer getLimit() {
            return limit;
        }

        public Integer getOffset() {
            return offset;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public void setFavorited(String favorited) {
            this.favorited = favorited;
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
