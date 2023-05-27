package io.github.juniqlim.realworld.user;

import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateUser {
    private final UserRepository userRepository;

    public UpdateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User update(Request request) {
        User user = userRepository.findByToken(request.jwsToken());
        if (request.password != null) {
            user = user.updatePassword(request.password);
        }
        if (request.email != null) {
            user = user.updateEmail(request.email);
        }
        if (request.username != null) {
            user = user.updateUsername(request.username);
        }
        if (request.bio != null) {
            user = user.updateBio(request.bio);
        }
        if (request.image != null) {
            user = user.updateImage(request.image);
        }
        userRepository.update(user);
        return user;
    }

    public static class Request {
        private final String jwsToken;
        private final String email;
        private final String username;
        private final String password;
        private final String bio;
        private final String image;

        private Request(String jwsToken, String email, String username, String password, String bio, String image) {
            this.jwsToken = jwsToken;
            this.email = email;
            this.username = username;
            this.password = password;
            this.bio = bio;
            this.image = image;
        }

        public static class Builder {
            private String jwsToken;
            private String email;
            private String username;
            private String password;
            private String bio;
            private String image;

            public Builder jwsToken(String jwsToken) {
                this.jwsToken = jwsToken;
                return this;
            }

            public Builder email(String email) {
                this.email = email;
                return this;
            }

            public Builder username(String username) {
                this.username = username;
                return this;
            }

            public Builder password(String password) {
                this.password = password;
                return this;
            }

            public Builder bio(String bio) {
                this.bio = bio;
                return this;
            }

            public Builder image(String image) {
                this.image = image;
                return this;
            }

            public Request build() {
                return new Request(jwsToken, email, username, password, bio, image);
            }
        }

        public String jwsToken() {
            return jwsToken;
        }

        public String email() {
            return email;
        }

        public String username() {
            return username;
        }

        public String password() {
            return password;
        }

        public String bio() {
            return bio;
        }

        public String image() {
            return image;
        }
    }
}
