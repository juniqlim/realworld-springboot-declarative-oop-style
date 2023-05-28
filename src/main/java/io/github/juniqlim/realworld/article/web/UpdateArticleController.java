package io.github.juniqlim.realworld.article.web;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.article.FindArticleResponse;
import io.github.juniqlim.realworld.article.UpdateArticle;
import io.github.juniqlim.realworld.article.UpdateArticle.Request.Builder;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.auth.HeaderAuthStringTo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateArticleController {
    private final UpdateArticle updateArticle;
    private final FindArticleResponse findArticleResponse;

    public UpdateArticleController(UpdateArticle updateArticle, FindArticleResponse findArticleResponse) {
        this.updateArticle = updateArticle;
        this.findArticleResponse = findArticleResponse;
    }

    @PutMapping("/api/articles/{slug}")
    public Response articles(@RequestHeader("Authorization") String headerAuthString, @PathVariable("slug") String slug,
        @RequestBody Request request) {
        Id loginUserId = HeaderAuthStringTo.userId(headerAuthString);
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
