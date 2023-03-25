package io.github.juniqlim.realworld.article.web

import io.github.juniqlim.realworld.article.domain.Article
import io.github.juniqlim.realworld.article.repository.ArticleRepository
import io.github.juniqlim.realworld.user.domain.User

class ArticleRDBRepository : ArticleRepository {
    override fun save(article: Article?) {
        TODO("Not yet implemented")
    }

    override fun findBySlug(slug: String?): Article {
        TODO("Not yet implemented")
    }

    override fun findBySlugAndUserId(slug: String?, userId: User.Id?): Article {
        TODO("Not yet implemented")
    }

    override fun update(slug: String?, article: Article?) {
        TODO("Not yet implemented")
    }

    override fun findByTagAuthorIdFavoriteUserIdOrderByRegdate(
        tag: String?,
        authorId: User.Id?,
        favoriteUserId: User.Id?,
        offset: Int,
        limit: Int
    ): MutableList<Article> {
        TODO("Not yet implemented")
    }

    override fun findByTagAuthorFavoriteUserOrderByRegdate(
        tag: String?,
        author: io.github.juniqlim.realworld.user.User?,
        favoriteUser: io.github.juniqlim.realworld.user.User?,
        offset: Int,
        limit: Int
    ): MutableList<Article> {
        TODO("Not yet implemented")
    }

    override fun delete(slug: String?, userId: User.Id?) {
        TODO("Not yet implemented")
    }

    override fun findCommentSequence(): Long {
        TODO("Not yet implemented")
    }

    override fun findByUserIds(followUsers: MutableList<User.Id>?, offset: Int, limit: Int): MutableList<Article> {
        TODO("Not yet implemented")
    }
}