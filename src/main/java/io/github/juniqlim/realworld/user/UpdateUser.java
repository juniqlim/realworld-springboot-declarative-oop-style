package io.github.juniqlim.realworld.user;

import io.github.juniqlim.realworld.Id;
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
        User user = userRepository.findById(request.loginUserId);
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
        private final Id loginUserId;
        private final String email;
        private final String username;
        private final String password;
        private final String bio;
        private final String image;

        public Request(Id loginUserId, String email, String username, String password, String bio, String image) {
            this.loginUserId = loginUserId;
            this.email = email;
            this.username = username;
            this.password = password;
            this.bio = bio;
            this.image = image;
        }
    }
}
