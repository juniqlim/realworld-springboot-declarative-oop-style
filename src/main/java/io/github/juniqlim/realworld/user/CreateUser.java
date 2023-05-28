package io.github.juniqlim.realworld.user;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.auth.CachedAuthToken;
import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateUser {
    private final UserRepository userRepository;

    public CreateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User user(Request request) {
        long userId = userRepository.findSequence();
        User user = new User(new Id.LongId(userId), CachedAuthToken.authToken().token(userId),
            request.password, request.username, request.email);
        userRepository.save(user);
        return user;
    }

    public static class Request {
        private final String username;
        private final String email;
        private final String password;

        public Request(String username, String email, String password) {
            this.username = username;
            this.email = email;
            this.password = password;
        }
    }
}
