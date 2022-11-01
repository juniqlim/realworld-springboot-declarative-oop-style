package io.github.juniqlim.realworld.user;

import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginUser {
    private final UserRepository userRepository;

    public LoginUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(Request request) {
        User user = userRepository.findByEmail(request.email());
        if (!user.equalsPassword(request.password())) {
            throw new IllegalArgumentException("Invalid password");
        }
        return user;
    }

    public static class Request {
        private final String email;
        private final String password;

        public Request(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String email() {
            return email;
        }

        public String password() {
            return password;
        }
    }
}
