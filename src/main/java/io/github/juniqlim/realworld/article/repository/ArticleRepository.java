package io.github.juniqlim.realworld.article.repository;

import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.user.domain.User;
import java.util.List;

public interface ArticleRepository {
    void save(Article article);

    Article findBySlug(String slug);

    Article findBySlugAndUserId(String slug, User.Id userId);

    void update(String slug, Article article);

    List<Article> findByTagAuthorIdFavoriteUserIdOrderByRegdate(String tag, User.Id authorId, User.Id favoriteUserId, int offset, int limit);

    List<Article> findByTagAuthorFavoriteUserOrderByRegdate(String tag,
        io.github.juniqlim.realworld.user.User author, io.github.juniqlim.realworld.user.User favoriteUser, int offset,
        int limit);

    void delete(String slug, User.Id userId);

    long findCommentSequence();

    List<Article> findByUserIds(List<User.Id> followUsers, int offset, int limit);

    class Conditional {
        private final String tag;
        private final User.Id authorId;
        private final User.Id favoriteUserId;

        public Conditional(String tag, User.Id authorId, User.Id favoriteUserId) {
            this.tag = tag;
            this.authorId = authorId;
            this.favoriteUserId = favoriteUserId;
        }

        public boolean value(Article article) {
            boolean isEqualsTag = false, isEqualsAuthorId = false, isEqualsFavoriteUserId = false;
            if (tag == null || article.equalsTag(tag)) {
                isEqualsTag = true;
            }
            if (authorId == null || article.equalsAuthorId(authorId)) {
                isEqualsAuthorId = true;
            }
            if (favoriteUserId == null || article.isFavorite(favoriteUserId)) {
                isEqualsFavoriteUserId = true;
            }
            return isEqualsTag && isEqualsAuthorId && isEqualsFavoriteUserId;
        }
    }

    class Conditional2 {
        private final String tag;
        private final io.github.juniqlim.realworld.user.User author;
        private final io.github.juniqlim.realworld.user.User favoriteUser;

        public Conditional2(String tag, io.github.juniqlim.realworld.user.User author, io.github.juniqlim.realworld.user.User favoriteUser) {
            this.tag = tag;
            this.author = author;
            this.favoriteUser = favoriteUser;
        }

        public boolean value(Article article) {
            boolean isEqualsTag = false, isEqualsAuthorId = false, isEqualsFavoriteUserId = false;
            if (tag == null || article.equalsTag(tag)) {
                isEqualsTag = true;
            }
            if (author.isNull() || article.equalsAuthorId(author.id())) {
                isEqualsAuthorId = true;
            }
            if (favoriteUser.isNull() || article.isFavorite(favoriteUser.id())) {
                isEqualsFavoriteUserId = true;
            }
            return isEqualsTag && isEqualsAuthorId && isEqualsFavoriteUserId;
        }
    }
}
