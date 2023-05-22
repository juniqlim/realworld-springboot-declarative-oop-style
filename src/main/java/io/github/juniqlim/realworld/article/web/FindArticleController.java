package io.github.juniqlim.realworld.article.web;

import io.github.juniqlim.realworld.article.FindArticleResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FindArticleController {
    private final FindArticleResponse findArticleResponse;

    FindArticleController(FindArticleResponse findArticleResponse) {
        this.findArticleResponse = findArticleResponse;
    }

    @GetMapping("/api/articles/{slug}")
    public Response articles(@PathVariable("slug") String slug) {
        return new Response(findArticleResponse.find(slug));
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
