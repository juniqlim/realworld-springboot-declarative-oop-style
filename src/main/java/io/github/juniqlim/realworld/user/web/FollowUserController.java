package io.github.juniqlim.realworld.user.web;

import io.github.juniqlim.object.jwt.VerifiedJwt;
import io.github.juniqlim.realworld.user.FollowUser;
import io.github.juniqlim.realworld.user.domain.Profile;
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
    public Response follow(@RequestHeader("Authorization") String token, @PathVariable String followeeUsername) {
        return new Response(followUser.follow(new Token(publicKey, token).jwsToken(), followeeUsername));
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
