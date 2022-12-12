package io.github.juniqlim.realworld.article.web;

import io.github.juniqlim.realworld.article.FindArticle;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.user.FindProfile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FindArticleController {
    private final FindArticle findArticle;
    private final FindProfile findProfile;

    FindArticleController(FindArticle findArticle, FindProfile findProfile) {
        this.findArticle = findArticle;
        this.findProfile = findProfile;
    }

    @GetMapping("/api/articles/{slug}")
    public Response articles(@PathVariable("slug") String slug) {
        Article article = findArticle.find(slug);
        return new Response(new ArticleResponse(
            article,
            findProfile.profile(article.authorId()))
        );
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
