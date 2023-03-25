package io.github.juniqlim.realworld.article.web

import io.github.juniqlim.realworld.article.repository.ArticleMemoryRepository
import io.github.juniqlim.realworld.user.FindUser
import io.github.juniqlim.realworld.user.repository.UserRepository
import org.junit.jupiter.api.Test

class KtFindArticleResponseTest {
    @Test
    fun test() {
        KtFindArticleResponse(ArticleMemoryRepository(), FindUser(UserRepository.Collection()))
            .find(KtFindArticleResponse.Request(null, null, null, null, 0, 20))
    }
}