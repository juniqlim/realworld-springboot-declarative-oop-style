package io.github.juniqlim.realworld.article.web

import io.github.juniqlim.realworld.article.FindArticleResponse
import io.github.juniqlim.realworld.user.FindUser
import io.github.juniqlim.realworld.user.User.UserByName
import io.github.juniqlim.realworld.user.User.UserByToken
import io.github.juniqlim.realworld.user.web.Token
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import java.security.PublicKey

@RestController
private class KtFindArticlesController(
    val findArticleResponse: FindArticleResponse,
    val findUser: FindUser,
    val publicKey: PublicKey
) {
    @GetMapping("/api/kt/articles")
    private fun findArticles(
        @RequestHeader(name = "Authorization", required = false) token: String?,
        request: Request
    ): Response {
        return Response(
            findArticleResponse.find(
                FindArticleResponse.Request.Builder()
                    .user(UserByToken(findUser, Token.Jws(publicKey, token)))
                    .tag(request.tag)
                    .author(UserByName(findUser, request.author))
                    .favoriteUser(UserByName(findUser, request.favorited))
                    .limit(request.limit)
                    .offset(request.offset)
                    .build()
            )
        )
    }

    private class Request(var tag: String?, var author: String?, var favorited: String?) {
        var limit = 20
        var offset = 0
    }

    class Response(val articles: List<ArticleResponse>)
}
