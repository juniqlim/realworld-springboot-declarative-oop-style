package io.github.juniqlim.realworld.article.web;

import io.github.juniqlim.realworld.article.FindArticle;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.user.FindUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FindArticleController {
    private final FindArticle findArticle;
    private final FindUser findUser;

    FindArticleController(FindArticle findArticle, FindUser findUser) {
        this.findArticle = findArticle;
        this.findUser = findUser;
    }

    @GetMapping("/api/articles/{slug}")
    public Response articles(@PathVariable("slug") String slug) {
        Article article = findArticle.find(slug);
        return new Response(new ArticleResponse(
            article,
            findUser.find(article.authorId()).profile())
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
