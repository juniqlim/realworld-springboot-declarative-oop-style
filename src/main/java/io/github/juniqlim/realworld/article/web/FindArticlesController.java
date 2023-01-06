package io.github.juniqlim.realworld.article.web;

import io.github.juniqlim.realworld.article.FindArticleResponse;
import io.github.juniqlim.realworld.user.web.NullOrToken;
import java.security.PublicKey;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FindArticlesController {
    private final FindArticleResponse findArticleResponse;
    private final PublicKey publicKey;

    FindArticlesController(FindArticleResponse findArticleResponse, PublicKey publicKey) {
        this.findArticleResponse = findArticleResponse;
        this.publicKey = publicKey;
    }

    @GetMapping("/api/articles")
    public Response articles(@RequestHeader(name = "Authorization", required = false) String token, Request request) {
        Request nullOrRequest = new NullOrRequest(request).value();
        return new Response(findArticleResponse.find(
            new FindArticleResponse.Request.Builder()
                .jwtToken(new NullOrToken(publicKey, token).jwsToken())
                .tag(nullOrRequest.tag)
                .authorName(nullOrRequest.author)
                .favoriteUserName(nullOrRequest.favorited)
                .limit(nullOrRequest.limit)
                .offset(nullOrRequest.offset)
                .build())
        );
    }

    private static class Request {
        private String tag;
        private String author;
        private String favorited;
        private Integer limit;
        private Integer offset;

        public Request() {
        }

        public Request(String tag, String author, String favorited, Integer limit, Integer offset) {
            this.tag = tag;
            this.author = author;
            this.favorited = favorited;
            this.limit = limit;
            this.offset = offset;
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

    static class NullOrRequest {
        private final Request request;

        public NullOrRequest(Request request) {
            this.request = request;
        }

        public Request value() {
            if (request == null) {
                return new Request(null, null, null, 20, 0);
            }
            return request;
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
