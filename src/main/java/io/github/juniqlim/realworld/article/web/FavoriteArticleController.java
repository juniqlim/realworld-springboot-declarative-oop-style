package io.github.juniqlim.realworld.article.web;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.article.FavoriteArticle;
import io.github.juniqlim.realworld.article.FindArticleResponse;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.web.Token;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;

@RestController
public class FavoriteArticleController {
    private final FavoriteArticle favoriteArticle;
    private final FindArticleResponse findArticleResponse;
    private final FindUser findUser;
    private final PublicKey publicKey;

    public FavoriteArticleController(FavoriteArticle favoriteArticle, FindArticleResponse findArticleResponse, FindUser findUser, PublicKey publicKey) {
        this.favoriteArticle = favoriteArticle;
        this.findArticleResponse = findArticleResponse;
        this.findUser = findUser;
        this.publicKey = publicKey;
    }

    @PostMapping("/api/articles/{slug}/favorite")
    public Response favoriteArticle(@RequestHeader("Authorization") String token, @PathVariable String slug) {
        Id loginUserId = findUser.find(new Token.Jws(publicKey, token).value()).id();
        favoriteArticle.favorite(slug, loginUserId);
        return new Response(findArticleResponse.find(slug, loginUserId));
    }

    @DeleteMapping("/api/articles/{slug}/favorite")
    public Response unfavoriteArticle(@RequestHeader("Authorization") String token, @PathVariable String slug) {
        Id loginUserId = findUser.find(new Token.Jws(publicKey, token).value()).id();
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
