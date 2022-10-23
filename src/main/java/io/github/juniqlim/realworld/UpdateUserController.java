package io.github.juniqlim.realworld;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateUserController {
    private final UpdateUser updateUser;

    public UpdateUserController(UpdateUser updateUser) {
        this.updateUser = updateUser;
    }

    @PutMapping("/api/user")
    public Response update(@RequestBody Request request) {
        return new Response(updateUser.update(request.updateRequest()));
    }

    private static class Request {
        private User user;

        public User getUser() {
            return user;
        }

        UpdateUser.Request updateRequest() {
            return new UpdateUser.Request(user.getEmail(), user.getBio(), user.getImage());
        }

        static class User {
            private final String email;
            private final String bio;
            private final String image;

            public User(String email, String bio, String image) {
                this.email = email;
                this.bio = bio;
                this.image = image;
            }

            public String getEmail() {
                return email;
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

        private Response(io.github.juniqlim.realworld.User user) {
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
