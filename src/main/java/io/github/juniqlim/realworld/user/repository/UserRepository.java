package io.github.juniqlim.realworld.user.repository;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.user.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public interface UserRepository {
    void save(User user);
    User findById(Id userId);
    User findByEmail(String email);
    User findByUsername(String username);
    User findByToken(String token);
    void update(User user);
    long findSequence();
    List<User> findByIds(List<Id> userIds);
    void follow(Id followerUserId, Id followeeUserId);
    void unFollow(Id followerUserId, Id followeeUserId);
    boolean isFollowing(Id followerUserId, Id followeeUserId);
    List<Id> followingUserIds(Id followerUserId);

    @Repository
    class UserArrayListRepository implements UserRepository {
        private final List<User> users = new ArrayList<>();
        private final AtomicLong sequence = new AtomicLong(1);
        private final List<UserFollow> userFollows = new ArrayList<>();
        public void save(User user) {
            users.add(user);
        }

        @Override
        public User findById(Id userId) {
            return users.stream()
                .filter(user -> user.id() == userId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        }

        public User findByToken(String token) {
            return users.stream()
                .filter(user -> user.token().equals(token))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        }

        public User findByEmail(String email) {
            return users.stream()
                .filter(user -> user.email().equals(email))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        }

        public User findByUsername(String username) {
            return users.stream()
                .filter(user -> user.username().equals(username))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        }

        public void update(User update) {
            users.removeIf(user -> user.token().equals(update.token()));
            users.add(update);
        }

        public long findSequence() {
            return sequence.getAndIncrement();
        }

        @Override
        public List<User> findByIds(List<Id> userIds) {
            return users.stream()
                .filter(user -> userIds.contains(user.id()))
                .collect(Collectors.toList());
        }

        @Override
        public void follow(Id followerUserId, Id followeeUserId) {
            userFollows.add(new UserFollow(followerUserId, followeeUserId));
        }

        @Override
        public void unFollow(Id followerUserId, Id followeeUserId) {
            userFollows.remove(new UserFollow(followerUserId, followeeUserId));
        }

        @Override
        public boolean isFollowing(Id followerUserId, Id followeeUserId) {
            return userFollows.contains(new UserFollow(followerUserId, followeeUserId));
        }

        @Override
        public List<Id> followingUserIds(Id followerUserId) {
            return userFollows.stream()
                .filter(userFollow -> userFollow.followerUserId().equals(followerUserId))
                .map(UserFollow::followeeUserId)
                .collect(Collectors.toList());
        }

        private static class UserFollow {
            private final Id followerUserId;
            private final Id followeeUserId;

            public UserFollow(Id followerUserId, Id followeeUserId) {
                this.followerUserId = followerUserId;
                this.followeeUserId = followeeUserId;
            }

            public Id followerUserId() {
                return followerUserId;
            }

            public Id followeeUserId() {
                return followeeUserId;
            }
        }
    }
}
