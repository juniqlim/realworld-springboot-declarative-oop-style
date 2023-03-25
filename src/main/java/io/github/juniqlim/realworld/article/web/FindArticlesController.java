package io.github.juniqlim.realworld.article.web;

import io.github.juniqlim.realworld.article.FindArticleResponse;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.User.UserByName;
import io.github.juniqlim.realworld.user.User.UserByToken;
import io.github.juniqlim.realworld.user.web.Token.Jws;
import java.security.PublicKey;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FindArticlesController {
    private final KtFindArticleResponse findArticleResponse;
    private final FindUser findUser;
    private final PublicKey publicKey;

    FindArticlesController(KtFindArticleResponse findArticleResponse, FindUser findUser, PublicKey publicKey) {
        this.findArticleResponse = findArticleResponse;
        this.findUser = findUser;
        this.publicKey = publicKey;
    }

    @GetMapping("/api/articles")
    public Response articles(@RequestHeader(name = "Authorization", required = false) String token, Request request) {
        return new Response(findArticleResponse.find(
            new KtFindArticleResponse.Request(
                new UserByToken(findUser, new Jws(publicKey, token)), request.tag, new UserByName(findUser, request.author), new UserByName(findUser, request.favorited), request.limit, request.offset
            )
        ));
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
