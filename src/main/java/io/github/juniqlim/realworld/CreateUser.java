package io.github.juniqlim.realworld;

import io.github.juniqlim.object.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class CreateUser {
    private final UserRepository userRepository;
    private final Key key;

    public CreateUser(UserRepository userRepository, Key key) {
        this.userRepository = userRepository;
        this.key = key;
    }

    public User user(Request request) {
        User user = request.user(new Jwt.Jws(key));
        userRepository.save(user);
        return user;
    }

    static class Request {
        private final String username;
        private final String email;
        private final String password;

        public Request(String username, String email, String password) {
            this.username = username;
            this.email = email;
            this.password = password;
        }

        public User user(Jwt token) {
            return new User(token.token(), password, username, email);
        }
    }
}
