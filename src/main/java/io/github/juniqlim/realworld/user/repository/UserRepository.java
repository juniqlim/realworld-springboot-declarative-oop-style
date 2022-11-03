package io.github.juniqlim.realworld.user.repository;

import io.github.juniqlim.realworld.user.domain.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private final List<User> users = new ArrayList<>();
    public void save(User user) {
        users.add(user);
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
}
