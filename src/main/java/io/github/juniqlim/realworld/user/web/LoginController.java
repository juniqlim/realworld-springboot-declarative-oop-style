package io.github.juniqlim.realworld.user.web;

import io.github.juniqlim.realworld.user.LoginUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final LoginUser login;

    public LoginController(LoginUser login) {
        this.login = login;
    }

    @PostMapping("/api/users/login")
    public Response login(@RequestBody Request request) {
        return new Response(login.login(request.loginRequest()));
    }

    private static class Request {
        private User user;

        public User getUser() {
            return user;
        }

        static class User {
            private final String email;
            private final String password;

            public User(String email, String password) {
                this.email = email;
                this.password = password;
            }

            public String getEmail() {
                return email;
            }

            public String getPassword() {
                return password;
            }
        }

        LoginUser.Request loginRequest() {
            return new LoginUser.Request(user.getEmail(), user.getPassword());
        }
    }

    private static class Response {
        private final Response.User user;

        private Response(io.github.juniqlim.realworld.user.domain.User user) {
            this(new Response.User(user));
        }

        private Response(Response.User user) {
            this.user = user;
        }

        public Response.User getUser() {
            return user;
        }

        private static class User {
            private final String email;
            private final String token;
            private final String username;
            private final String bio;
            private final String image;

            private User(io.github.juniqlim.realworld.user.domain.User user) {
                this.email = user.email();
                this.token = user.token();
                this.username = user.username();
                this.bio = user.bio();
                this.image = user.image();
            }

            public String getEmail() {
                return email;
            }

            public String getToken() {
                return token;
            }

            public String getUsername() {
                return username;
            }

            public String getBio() {
                return bio;
            }

            public String getImage() {
                return image;
            }
        }
    }
}
