package io.github.juniqlim.realworld.user;

import io.github.juniqlim.object.jwt.Jws;
import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.repository.UserRepository;
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
        User user = request.user(new Jws.RsaJws(privateKey).token(), userRepository.findSequence());
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

        public User user(String token, long sequence) {
            return new User(new Id.LongId(sequence), token, password, username, email);
        }
    }
}
