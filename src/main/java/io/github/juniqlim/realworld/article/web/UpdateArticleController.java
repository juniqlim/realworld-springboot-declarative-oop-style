package io.github.juniqlim.realworld.article.web;

import io.github.juniqlim.realworld.article.UpdateArticle;
import io.github.juniqlim.realworld.article.UpdateArticle.Request.Builder;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.web.Token;
import java.security.PublicKey;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateArticleController {
    private final UpdateArticle updateArticle;
    private final FindUser findUser;
    private final PublicKey publicKey;

    UpdateArticleController(UpdateArticle updateArticle, FindUser findUser, PublicKey publicKey) {
        this.updateArticle = updateArticle;
        this.findUser = findUser;
        this.publicKey = publicKey;
    }

    @PutMapping("/api/articles/{slug}")
    public Response articles(@RequestHeader("Authorization") String token, @PathVariable("slug") String slug,
        @RequestBody Request request) {
        User loginUser = findUser.find(new Token.Jws(publicKey, token).value());
        UpdateArticle.Request updateRequest = new Builder()
            .userId(loginUser.id())
            .slug(slug)
            .title(request.getArticle().getTitle())
            .description(request.getArticle().getDescription())
            .body(request.getArticle().getBody())
            .build();
        return new Response(
            new ArticleResponse(updateArticle.update(updateRequest), loginUser.profile(), loginUser.id())
        );
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
