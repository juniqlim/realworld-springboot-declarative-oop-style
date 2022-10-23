package io.github.juniqlim.realworld;

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
        return new Response(createUser.user(request.createUserRequest()));
    }

    private static class Request {
        private User user;

        public User getUser() {
            return user;
        }

        private CreateUser.Request createUserRequest() {
            return new CreateUser.Request(user.getUsername(), user.getEmail(), user.getPassword());
        }

        private static class User {
            private final String username;
            private final String email;
            private final String password;

            private User(String username, String email, String password) {
                this.username = username;
                this.email = email;
                this.password = password;
            }

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

    private static class Response {
        private final User user;

        private Response(io.github.juniqlim.realworld.User user) {
            this(new User(user));
        }

        private Response(User user) {
            this.user = user;
        }

        public User getUser() {
            return user;
        }

        private static class User {
            private final String email;
            private final String token;
            private final String username;
            private final String bio;
            private final String image;

            private User(io.github.juniqlim.realworld.User user) {
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