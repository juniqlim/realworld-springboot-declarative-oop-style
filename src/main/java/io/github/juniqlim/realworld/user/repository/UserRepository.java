package io.github.juniqlim.realworld.user.repository;

import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.domain.User.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

public interface UserRepository {
    void save(User user);
    User findById(Id userId);
    User findByEmail(String email);
    User findByUsername(String username);
    User findByToken(String token);
    void update(User user);
    long findSequence();
    List<User> findByIds(List<User.Id> userIds);


    @Repository
    class Collection implements UserRepository {
        private final List<User> users = new ArrayList<>();
        private final AtomicLong sequence = new AtomicLong(1);
        public void save(User user) {
            users.add(user);
        }

        @Override
        public User findById(Id userId) {
            return users.stream()
                .filter(user -> user.equalsId(userId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        }

        public User findByToken(String token) {
            return users.stream()
                .filter(user -> user.equalsToken(token))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        }

        public User findByEmail(String email) {
            return users.stream()
                .filter(user -> user.equalsEmail(email))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        }

        public User findByUsername(String username) {
            return users.stream()
                .filter(user -> user.equalsUsername(username))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        }

        public void update(User update) {
            users.removeIf(user -> user.equalsToken(update.token()));
            users.add(update);
        }

        public List<User> findByTokens(List<String> userIds) {
            return users.stream()
                .filter(user -> userIds.contains(user.token()))
                .collect(Collectors.toList());
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
    }
}
