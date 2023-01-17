package io.github.juniqlim.realworld.user.web;

import io.github.juniqlim.realworld.user.UnfollowUser;
import io.github.juniqlim.realworld.user.domain.Profile;
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
    Response unfollow(@RequestHeader("Authorization") String token, @PathVariable String followeeUsername) {
        return new Response(unfollowUser.unfollow(new Token(publicKey, token).jwsToken(), followeeUsername));
    }

    private static class Response {
        private final Profile profile;

        private Response(io.github.juniqlim.realworld.user.domain.Profile profile) {
            this.profile = new Profile(profile);
        }

        public Profile getProfile() {
            return profile;
        }

        private static class Profile {
            private String username;
            private String bio;
            private String image;
            private boolean following;

            Profile(io.github.juniqlim.realworld.user.domain.Profile profile) {
                this.username = profile.username();
                this.bio = profile.bio();
                this.image = profile.image();
                this.following = profile.isFollowing();
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

            public boolean isFollowing() {
                return following;
            }
        }
    }
}
