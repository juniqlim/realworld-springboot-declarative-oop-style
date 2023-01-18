package io.github.juniqlim.realworld.user.web;

import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.web.Token.Jws;
import java.security.PublicKey;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FindProfileController {
    private final FindUser findUser;
    private final PublicKey publicKey;

    FindProfileController(FindUser findUser, PublicKey publicKey) {
        this.findUser = findUser;
        this.publicKey = publicKey;
    }

    @GetMapping("/api/profiles/{username}")
    public Response profile(@RequestHeader("Authorization") String token, @PathVariable("username") String username) {
        return new Response(findUser.find(new Jws(publicKey, token).value()).profile(findUser.findByUsername(username).id()));
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
