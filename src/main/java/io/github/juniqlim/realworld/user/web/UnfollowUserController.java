package io.github.juniqlim.realworld.user.web;

import io.github.juniqlim.object.jwt.VerifiedJwt;
import io.github.juniqlim.realworld.user.domain.Profile;
import io.github.juniqlim.realworld.user.UnfollowUser;
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
    Profile unfollow(@RequestHeader("Authorization") String token, @PathVariable String followeeUsername) {
        return unfollowUser.unfollow(new Token(publicKey, token).jwsToken(), followeeUsername);
    }
}
