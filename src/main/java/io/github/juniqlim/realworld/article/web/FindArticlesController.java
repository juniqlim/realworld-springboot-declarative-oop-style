package io.github.juniqlim.realworld.article.web;

import io.github.juniqlim.realworld.article.FindArticleResponse;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.User.UserByName;
import io.github.juniqlim.realworld.user.User.UserByToken;
import io.github.juniqlim.realworld.user.web.Token.Jws;
import java.security.PublicKey;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FindArticlesController {
    private final FindArticleResponse findArticleResponse;
    private final FindUser findUser;
    private final PublicKey publicKey;

    FindArticlesController(FindArticleResponse findArticleResponse, FindUser findUser, PublicKey publicKey) {
        this.findArticleResponse = findArticleResponse;
        this.findUser = findUser;
        this.publicKey = publicKey;
    }

    @GetMapping("/api/articles")
    public Response articles(@RequestHeader(name = "Authorization", required = false) String token, Request request) {
        return new Response(findArticleResponse.find(
            new FindArticleResponse.Request.Builder()
                .user(new UserByToken(findUser, new Jws(publicKey, token)))
                .tag(request.tag)
                .author(new UserByName(findUser, request.author))
                .favoriteUser(new UserByName(findUser, request.favorited))
                .limit(request.limit)
                .offset(request.offset)
                .build())
        );
    }

    private record Request(String tag, String author, String favorited, Integer limit, Integer offset) {
        private Request {
            if (limit == null) {
                limit = 20;
            }

            if (offset == null) {
                offset = 0;
            }
        }
    }

    private record Response(List<ArticleResponse> articles) {}
}
