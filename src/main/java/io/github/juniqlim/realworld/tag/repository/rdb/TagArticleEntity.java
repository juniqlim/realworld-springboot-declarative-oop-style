package io.github.juniqlim.realworld.tag.repository.rdb;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
class TagArticleEntity {
    @EmbeddedId
    private TagArticleId id;

    protected TagArticleEntity() {
    }

    public TagArticleEntity(TagArticleId id) {
        this.id = id;
    }

    public TagArticleId id() {
        return id;
    }

    @Embeddable
    static class TagArticleId implements Serializable {
        private long articleId;
        private long tagId;

        protected TagArticleId() {
        }

        public TagArticleId(long articleId, long tagId) {
            this.articleId = articleId;
            this.tagId = tagId;
        }

        public long articleId() {
            return articleId;
        }

        public long tagId() {
            return tagId;
        }
    }
}
