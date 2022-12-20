package io.github.juniqlim.realworld.article.web;

import io.github.juniqlim.realworld.article.FindArticleResponse;
import io.github.juniqlim.realworld.user.web.Token;
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
    public Response articles(@RequestHeader("Authorization") String token, @RequestParam Request request) {
        FindArticleResponse.Request request1 = new FindArticleResponse.Request(new Token(publicKey, token).jwsToken(),
            request.tag(), request.author(), request.favorited(), request.limit(), request.offset());
        return new Response(findArticleResponse.find(request1));
    }

    private static class Request {
        private String tag;
        private String author;
        private String favorited;
        private Integer limit;
        private Integer offset;

        String tag() {
            return tag;
        }

        String author() {
            return author;
        }

        String favorited() {
            return favorited;
        }

        Integer limit() {
            return limit;
        }

        Integer offset() {
            return offset;
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
