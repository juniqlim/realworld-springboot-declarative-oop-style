package io.github.juniqlim.realworld.user.web;

import io.github.juniqlim.realworld.user.CreateUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateUserController {
    private final CreateUser createUser;

    public CreateUserController(CreateUser createUser) {
        this.createUser = createUser;
    }

    @PostMapping("/api/users")
    public Response user(@RequestBody Request request) {
        return new Response(
            createUser.user(
                new CreateUser.Request(
                    request.getUser().getUsername(),
                    request.getUser().getEmail(),
                    request.getUser().getPassword()
                )
            )
        );
    }


    private static class Request {
        private User user;

        public User getUser() {
            return user;
        }

        private static class User {
            private String username;
            private String email;
            private String password;

            public String getUsername() {
                return username;
            }

            public String getEmail() {
                return email;
            }

            public String getPassword() {
                return password;
            }
        }
    }

    public static class Response {
        private final User user;

        private Response(io.github.juniqlim.realworld.user.domain.User user) {
            this(new User(user.email(), user.token(), user.username(), user.bio(), user.image()));
        }

        private Response(User user) {
            this.user = user;
        }

        public User getUser() {
            return user;
        }

        public static class User {
            private final String email;
            private final String token;
            private final String username;
            private final String bio;
            private final String image;

            public User(String email, String token, String username, String bio, String image) {
                this.email = email;
                this.token = token;
                this.username = username;
                this.bio = bio;
                this.image = image;
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
