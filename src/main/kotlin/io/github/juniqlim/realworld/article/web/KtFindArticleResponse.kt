package io.github.juniqlim.realworld.article.web

import io.github.juniqlim.realworld.article.domain.Article
import io.github.juniqlim.realworld.article.repository.ArticleRepository
import io.github.juniqlim.realworld.user.FindUser
import io.github.juniqlim.realworld.user.User
import io.github.juniqlim.realworld.user.domain.Profile
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class KtFindArticleResponse(private val articleRepository: ArticleRepository, private val findUser: FindUser) {
    fun find(slug: String): Article {
        return articleRepository.findBySlug(slug)
    }

    fun find(request: Request): List<ArticleResponse> {
        val articles = articleRepository.findByTagAuthorFavoriteUserOrderByRegdate(
            request.tag, request.author, request.favoriteUser,
            request.offset, request.limit
        )
        return articles.stream()
            .map { article: Article -> ArticleResponse(article, profile(request, article), request.user) }
            .collect(Collectors.toList())
    }

    fun profile(request: Request, article: Article): Profile? {
        try {
            return if (request.user!!.isExist) {
                findUser.find(article.authorId()).profile(request.user.id())
            } else findUser.find(article.authorId()).profile()
        } catch (e: IllegalArgumentException) {
            if (e.message == "User not found") {
                return null
            }
        }
        return null
    }

    class Request(
        internal val user: User?, internal val tag: String?,
        internal val author: User?, internal val favoriteUser: User?,
        internal val offset: Int, internal val limit: Int
    )
}