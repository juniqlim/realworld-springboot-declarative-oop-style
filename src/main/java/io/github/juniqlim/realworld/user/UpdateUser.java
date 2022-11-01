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

    public User update(Request updateRequest) {
        User findedUser = userRepository.findByToken(updateRequest.jwsToken());
        User updateUser = findedUser.update(updateRequest.email(), updateRequest.bio(), updateRequest.image());
        userRepository.update(updateUser);
        return updateUser;
    }

    public static class Request {
        private final String jwsToken;
        private final String email;
        private final String bio;
        private final String image;

        public Request(String jwsToken, String email, String bio, String image) {
            this.jwsToken = jwsToken;
            this.email = email;
            this.bio = bio;
            this.image = image;
        }

        public String jwsToken() {
            return jwsToken;
        }

        public String email() {
            return email;
        }

        public String bio() {
            return bio;
        }

        public String image() {
            return image;
        }
    }
}
