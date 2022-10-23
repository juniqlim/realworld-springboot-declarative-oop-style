package io.github.juniqlim.realworld;

import io.github.juniqlim.object.jwt.VerifiedJwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.security.PublicKey;

@RestController
public class FindUserController {
    private final FindUser findUser;
    private final PublicKey publicKey;

    public FindUserController(FindUser findUser, PublicKey publicKey) {
        this.findUser = findUser;
        this.publicKey = publicKey;
    }

    @GetMapping("/api/user")
    public Response user(@RequestHeader("Authorization") String jwsToken) {
        jwsToken = jwsToken.substring(6);
        verifyJws(jwsToken);
        return new Response(findUser.find(jwsToken));
    }

    private void verifyJws(String token) {
        if (!new VerifiedJwt.VerifiedJws(publicKey, token).verifiable()) {
            throw new IllegalArgumentException("Invalid jws");
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
