package io.github.juniqlim.realworld.article.web;

import io.github.juniqlim.realworld.article.FavoriteArticle;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.web.Token;
import java.security.PublicKey;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FavoriteArticleController {
    private final FavoriteArticle favoriteArticle;
    private final FindUser findUser;
    private final PublicKey publicKey;

    FavoriteArticleController(FavoriteArticle favoriteArticle, FindUser findUser, PublicKey publicKey) {
        this.favoriteArticle = favoriteArticle;
        this.findUser = findUser;
        this.publicKey = publicKey;
    }

    @PostMapping("/api/articles/{slug}/favorite")
    public Response favoriteArticle(@RequestHeader("Authorization") String token, @PathVariable String slug) {
        User.Id loginUserId = findUser.find(new Token.Jws(publicKey, token).value()).id();
        return new Response(new RappingArticle(findUser).value(favoriteArticle.favorite(slug, loginUserId), loginUserId));
    }

    @DeleteMapping("/api/articles/{slug}/favorite")
    public Response unfavoriteArticle(@RequestHeader("Authorization") String token, @PathVariable String slug) {
        User.Id loginUserId = findUser.find(new Token.Jws(publicKey, token).value()).id();
        return new Response(new RappingArticle(findUser).value(favoriteArticle.unFavorite(slug, loginUserId), loginUserId));
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
