package io.github.juniqlim.realworld;

import org.springframework.stereotype.Service;

@Service
class Login {
    private final UserRepository userRepository;

    public Login(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(Request request) {
        User user = userRepository.findByEmail(request.email());
        if (!user.equalsPassword(request.password())) {
            throw new IllegalArgumentException("Invalid password");
        }
        return user;
    }

    static class Request {
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
