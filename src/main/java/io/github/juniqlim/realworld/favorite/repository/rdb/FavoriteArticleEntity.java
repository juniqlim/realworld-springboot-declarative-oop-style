package io.github.juniqlim.realworld.favorite.repository.rdb;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
class FavoriteArticleEntity {
    @EmbeddedId
    private FavoriteArticleId id;
    private LocalDateTime createdAt;

    public FavoriteArticleEntity(FavoriteArticleId id) {
        this.id = id;
        this.createdAt = LocalDateTime.now();
    }

    protected FavoriteArticleEntity() {
    }

    public FavoriteArticleId id() {
        return id;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    @Embeddable
    static class FavoriteArticleId implements Serializable {
        private long articleId;
        private long favoriteUserId;

        public FavoriteArticleId(long articleId, long favoriteUserId) {
            this.articleId = articleId;
            this.favoriteUserId = favoriteUserId;
        }

        protected FavoriteArticleId() {
        }

        public long articleId() {
            return articleId;
        }

        public long favoriteUserId() {
            return favoriteUserId;
        }
    }
}
