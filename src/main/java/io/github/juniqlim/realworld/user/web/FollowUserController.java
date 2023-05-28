package io.github.juniqlim.realworld.user.web;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.auth.HeaderAuthStringTo;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.FollowUser;
import io.github.juniqlim.realworld.user.domain.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FollowUserController {
    private final FindUser findUser;
    private final FollowUser followUser;

    public FollowUserController(FindUser findUser, FollowUser followUser) {
        this.findUser = findUser;
        this.followUser = followUser;
    }

    @PostMapping("/api/profiles/{followeeUsername}/follow")
    public Response follow(@RequestHeader("Authorization") String headerAuthString, @PathVariable String followeeUsername) {
        Id followerUserId = HeaderAuthStringTo.userId(headerAuthString);
        User followeeUser = findUser.findByUsername(followeeUsername);
        followUser.follow(followerUserId, followeeUser.id());
        return new Response(followeeUser.profile(true));
    }

    @DeleteMapping("/api/profiles/{followeeUsername}/follow")
    Response unfollow(@RequestHeader("Authorization") String headerAuthString, @PathVariable String followeeUsername) {
        Id followerUserId = HeaderAuthStringTo.userId(headerAuthString);
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
