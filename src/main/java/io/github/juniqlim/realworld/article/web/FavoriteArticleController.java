package io.github.juniqlim.realworld.article.web;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.article.FavoriteArticle;
import io.github.juniqlim.realworld.article.FindArticleResponse;
import io.github.juniqlim.realworld.auth.HeaderAuthStringTo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FavoriteArticleController {
    private final FavoriteArticle favoriteArticle;
    private final FindArticleResponse findArticleResponse;

    public FavoriteArticleController(FavoriteArticle favoriteArticle, FindArticleResponse findArticleResponse) {
        this.favoriteArticle = favoriteArticle;
        this.findArticleResponse = findArticleResponse;
    }

    @PostMapping("/api/articles/{slug}/favorite")
    public Response favoriteArticle(@RequestHeader("Authorization") String headerAuthString, @PathVariable String slug) {
        Id loginUserId = HeaderAuthStringTo.userId(headerAuthString);
        favoriteArticle.favorite(slug, loginUserId);
        return new Response(findArticleResponse.find(slug, loginUserId));
    }

    @DeleteMapping("/api/articles/{slug}/favorite")
    public Response unfavoriteArticle(@RequestHeader("Authorization") String headerAuthString, @PathVariable String slug) {
        Id loginUserId = HeaderAuthStringTo.userId(headerAuthString);
        favoriteArticle.unFavorite(slug, loginUserId);
        return new Response(findArticleResponse.find(slug, loginUserId));
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
