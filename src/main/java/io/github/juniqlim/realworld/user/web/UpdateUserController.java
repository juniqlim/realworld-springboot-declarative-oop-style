package io.github.juniqlim.realworld.user.web;

import io.github.juniqlim.realworld.auth.HeaderAuthStringTo;
import io.github.juniqlim.realworld.user.UpdateUser;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateUserController {
    private final UpdateUser updateUser;

    public UpdateUserController(UpdateUser updateUser) {
        this.updateUser = updateUser;
    }

    @PutMapping("/api/user")
    public Response update(@RequestHeader("Authorization") String headerAuthString, @RequestBody Request request) {
        return new Response(
            updateUser.update(
                new UpdateUser.Request(
                    HeaderAuthStringTo.userId(headerAuthString),
                    request.user.email,
                    request.user.username,
                    request.user.password,
                    request.user.bio,
                    request.user.image
                )
            )
        );
    }

    private static class Request {
        private User user;

        public User getUser() {
            return user;
        }

        static class User {
            private String email;
            private String username;
            private String password;
            private String bio;
            private String image;

            public String getEmail() {
                return email;
            }

            public String getUsername() {
                return username;
            }

            public String getPassword() {
                return password;
            }

            public String getBio() {
                return bio;
            }

            public String getImage() {
                return image;
            }
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
