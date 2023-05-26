package io.github.juniqlim.realworld.user.repository.rdb;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
class UserFollowEntity {
    @EmbeddedId
    private Id id;

    protected UserFollowEntity() {
    }

    public UserFollowEntity(Id id) {
        this.id = id;
    }

    public Id id() {
        return id;
    }

    @Embeddable
    static class Id implements Serializable {
        private long followerUserId;
        private long followeeUserId;

        protected Id() {
        }

        public Id(long followerUserId, long followeeUserId) {
            this.followerUserId = followerUserId;
            this.followeeUserId = followeeUserId;
        }

        public long followerUserId() {
            return followerUserId;
        }

        public long followeeUserId() {
            return followeeUserId;
        }
    }
}
