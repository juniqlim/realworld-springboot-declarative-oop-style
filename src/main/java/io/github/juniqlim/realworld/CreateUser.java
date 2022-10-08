package io.github.juniqlim.realworld;

public class CreateUser {
    public User user(Request request) {
        return null;
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
    }
}
