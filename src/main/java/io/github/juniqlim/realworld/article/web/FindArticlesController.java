package io.github.juniqlim.realworld.article.web;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.article.FindArticleResponse;
import io.github.juniqlim.realworld.auth.HeaderAuthStringTo;
import io.github.juniqlim.realworld.user.FindUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FindArticlesController {
    private final FindArticleResponse findArticleResponse;
    private final FindUser findUser;

    public FindArticlesController(FindArticleResponse findArticleResponse, FindUser findUser) {
        this.findArticleResponse = findArticleResponse;
        this.findUser = findUser;
    }

    @GetMapping("/api/articles")
    public Response articles(@RequestHeader(name = "Authorization", required = false) String headerAuthString, Request request) {
        return new Response(findArticleResponse.find(
            new FindArticleResponse.Request.Builder()
                .loginUserId(HeaderAuthStringTo.userId(headerAuthString))
                .tag(request.tag)
                .authorUserId(username(request.author))
                .favoriteUserId(username(request.favorited))
                .limit(request.limit)
                .offset(request.offset)
                .build())
        );
    }

    @GetMapping("/api/articles/feed")
    public Response feedArticles(@RequestHeader(name = "Authorization") String headerAuthString, Request request) {
        Id loginUserId = HeaderAuthStringTo.userId(headerAuthString);
        return new Response(
            findArticleResponse.findFeed(new FindArticleResponse.Request.Builder()
                .loginUserId(loginUserId)
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
