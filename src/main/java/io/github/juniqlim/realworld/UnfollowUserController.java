package io.github.juniqlim.realworld;

import io.github.juniqlim.object.jwt.VerifiedJwt;
import java.security.PublicKey;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UnfollowUserController {
    private final UnfollowUser unfollowUser;
    private final PublicKey publicKey;

    UnfollowUserController(UnfollowUser unfollowUser, PublicKey publicKey) {
        this.unfollowUser = unfollowUser;
        this.publicKey = publicKey;
    }

    @DeleteMapping("/api/profiles/{followeeUsername}/follow")
    Profile unfollow(@RequestHeader("Authorization") String jwsToken, @PathVariable String followeeUsername) {
        jwsToken = jwsToken.substring(6);
        verifyJws(jwsToken);
        return unfollowUser.unfollow(jwsToken, followeeUsername);
    }

    private void verifyJws(String token) {
        if (!new VerifiedJwt.VerifiedJws(publicKey, token).verifiable()) {
            throw new IllegalArgumentException("Invalid jws");
        }
    }
}
