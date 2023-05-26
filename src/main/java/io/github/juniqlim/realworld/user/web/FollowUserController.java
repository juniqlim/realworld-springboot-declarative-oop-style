package io.github.juniqlim.realworld.user.web;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.FollowUser;
import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.web.Token.Jws;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;

@RestController
public class FollowUserController {
    private final FindUser findUser;
    private final FollowUser followUser;
    private final PublicKey publicKey;

    public FollowUserController(FindUser findUser, FollowUser followUser, PublicKey publicKey) {
        this.findUser = findUser;
        this.followUser = followUser;
        this.publicKey = publicKey;
    }

    @PostMapping("/api/profiles/{followeeUsername}/follow")
    public Response follow(@RequestHeader("Authorization") String token, @PathVariable String followeeUsername) {
        Id followerUserId = findUser.findIdByToken(new Jws(publicKey, token));
        User followeeUser = findUser.findByUsername(followeeUsername);
        followUser.follow(followerUserId, followeeUser.id());
        return new Response(followeeUser.profile(true));
    }

    @DeleteMapping("/api/profiles/{followeeUsername}/follow")
    Response unfollow(@RequestHeader("Authorization") String token, @PathVariable String followeeUsername) {
        Id followerUserId = findUser.findIdByToken(new Jws(publicKey, token));
        User followeeUser = findUser.findByUsername(followeeUsername);
        followUser.unFollow(followerUserId, followeeUser.id());
        return new Response(followeeUser.profile(false));
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
