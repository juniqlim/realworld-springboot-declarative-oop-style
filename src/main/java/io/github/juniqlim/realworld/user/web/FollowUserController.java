package io.github.juniqlim.realworld.user.web;

import io.github.juniqlim.realworld.user.FollowUser;
import io.github.juniqlim.realworld.user.web.Token.Jws;
import java.security.PublicKey;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

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
        return new Response(followUser.follow(new Jws(publicKey, token).value(), followeeUsername));
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
