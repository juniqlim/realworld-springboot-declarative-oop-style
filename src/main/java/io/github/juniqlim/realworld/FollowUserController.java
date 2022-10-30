package io.github.juniqlim.realworld;

import io.github.juniqlim.object.jwt.VerifiedJwt;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.security.PublicKey;

@RestController
public class FollowUserController {
    private final FollowUser followUser;
    private final PublicKey publicKey;

    public FollowUserController(FollowUser followUser, PublicKey publicKey) {
        this.followUser = followUser;
        this.publicKey = publicKey;
    }

    @PostMapping("/api/profiles/{followeeUsername}/follow")
    public Response follow(@RequestHeader("Authorization") String jwsToken, @PathVariable String followeeUsername) {
        jwsToken = jwsToken.substring(6);
        verifyJws(jwsToken);
        return new Response(followUser.follow(jwsToken, followeeUsername));
    }

    private void verifyJws(String token) {
        if (!new VerifiedJwt.VerifiedJws(publicKey, token).verifiable()) {
            throw new IllegalArgumentException("Invalid jws");
        }
    }

    private static class Response {
        private final Profile profile;

        private Response(Profile profile) {
            this.profile = profile;
        }

        public Profile getProfile() {
            return profile;
        }
    }
}
