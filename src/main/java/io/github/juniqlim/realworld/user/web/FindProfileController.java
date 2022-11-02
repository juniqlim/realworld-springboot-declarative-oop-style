package io.github.juniqlim.realworld.user.web;

import io.github.juniqlim.object.jwt.VerifiedJwt;
import io.github.juniqlim.realworld.user.FindProfile;
import io.github.juniqlim.realworld.user.domain.Profile;
import io.github.juniqlim.realworld.user.domain.User;
import java.security.PublicKey;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FindProfileController {
    private final FindProfile findProfile;
    private final PublicKey publicKey;

    public FindProfileController(FindProfile findProfile, PublicKey publicKey) {
        this.findProfile = findProfile;
        this.publicKey = publicKey;
    }

    @GetMapping("/api/profiles/{username}")
    public Response profile(@RequestHeader("Authorization") String token, @PathVariable("username") String username) {
        return new Response(findProfile.profile(new Token(publicKey, token).jwsToken(), username));
    }

    private static class Response {
        private final Profile profile;

        private Response(User user) {
            this(new Profile(user, false));
        }

        private Response(Profile profile) {
            this.profile = profile;
        }

        public Profile getProfile() {
            return profile;
        }
    }
}
