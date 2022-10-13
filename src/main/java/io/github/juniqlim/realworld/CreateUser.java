package io.github.juniqlim.realworld;

public class CreateUser {
    private final UserRepository userRepository;
    private final Token token;

    public CreateUser(UserRepository userRepository, Token token) {
        this.userRepository = userRepository;
        this.token = token;
    }

    public User user(Request request) {
        User user = request.user(token);
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

        public User user(Token token) {
            return new User(token.token(), password, username, email);
        }
    }
}
