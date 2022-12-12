package io.github.juniqlim.realworld.article.web;

import io.github.juniqlim.realworld.article.UpdateArticle;
import io.github.juniqlim.realworld.article.UpdateArticle.Request.Builder;
import io.github.juniqlim.realworld.user.FindProfile;
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
    private final FindProfile findProfile;
    private final PublicKey publicKey;

    UpdateArticleController(UpdateArticle updateArticle, FindProfile findProfile, PublicKey publicKey) {
        this.updateArticle = updateArticle;
        this.findProfile = findProfile;
        this.publicKey = publicKey;
    }

    @PutMapping("/api/articles/{slug}")
    public Response articles(@RequestHeader("Authorization") String token, @PathVariable("slug") String slug,
        @RequestBody Request request) {
        String jwsToken = new Token(publicKey, token).jwsToken();
        return new Response(new ArticleResponse(
            updateArticle.update(new Builder()
                .jwsToken(new Token(publicKey, token).jwsToken())
                .slug(slug)
                .title(request.getArticle().getTitle())
                .description(request.getArticle().getDescription())
                .body(request.getArticle().getBody())
                .build()),
            findProfile.profile(jwsToken))
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
