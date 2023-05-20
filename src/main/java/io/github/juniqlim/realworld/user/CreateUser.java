package io.github.juniqlim.realworld.user;

import io.github.juniqlim.object.jwt.Jwt;
import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import java.security.PrivateKey;
import org.springframework.stereotype.Service;

@Service
public class CreateUser {
    private final UserRepository userRepository;
    private final PrivateKey privateKey;

    public CreateUser(UserRepository userRepository, PrivateKey privateKey) {
        this.userRepository = userRepository;
        this.privateKey = privateKey;
    }

    public User user(Request request) {
        User user = request.user(new Jwt.Jws(privateKey), userRepository.findSequence());
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

        public User user(Jwt token, long sequence) {
            return new User(sequence, token.token(), password, username, email);
        }
    }
}
