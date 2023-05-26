package io.github.juniqlim.realworld.article.web;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.article.FindArticleResponse;
import io.github.juniqlim.realworld.article.UpdateArticle;
import io.github.juniqlim.realworld.article.UpdateArticle.Request.Builder;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.web.Token;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;

@RestController
public class UpdateArticleController {
    private final UpdateArticle updateArticle;
    private final FindUser findUser;
    private final PublicKey publicKey;
    private final FindArticleResponse findArticleResponse;

    public UpdateArticleController(UpdateArticle updateArticle, FindUser findUser, PublicKey publicKey, FindArticleResponse findArticleResponse) {
        this.updateArticle = updateArticle;
        this.findUser = findUser;
        this.publicKey = publicKey;
        this.findArticleResponse = findArticleResponse;
    }

    @PutMapping("/api/articles/{slug}")
    public Response articles(@RequestHeader("Authorization") String token, @PathVariable("slug") String slug,
        @RequestBody Request request) {
        Id loginUserId = findUser.findIdByToken(new Token.Jws(publicKey, token));
        UpdateArticle.Request updateRequest = new Builder()
            .userId(loginUserId)
            .slug(slug)
            .title(request.getArticle().getTitle())
            .description(request.getArticle().getDescription())
            .body(request.getArticle().getBody())
            .build();
        Article updatedArticle = updateArticle.update(updateRequest);
        return new Response(findArticleResponse.find(updatedArticle.slug(), loginUserId));
    }

    private static class Request {
        private Article article;

        public Article getArticle() {
            return article;
        }

        private static class Article {
            private String title;
            private String description;
            private String body;

            public String getTitle() {
                return title;
            }

            public String getDescription() {
                return description;
            }

            public String getBody() {
                return body;
            }
        }
    }

    private static class Response {
        private final ArticleResponse article;

        Response(ArticleResponse article) {
            this.article = article;
        }

        public ArticleResponse getArticle() {
            return article;
        }
    }
}
