package io.github.juniqlim.realworld;

import io.github.juniqlim.object.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;

@Service
public class CreateUser {
    private final UserRepository userRepository;
    private final PrivateKey privateKey;

    public CreateUser(UserRepository userRepository, PrivateKey privateKey) {
        this.userRepository = userRepository;
        this.privateKey = privateKey;
    }

    public User user(Request request) {
        User user = request.user(new Jwt.Jws(privateKey));
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
