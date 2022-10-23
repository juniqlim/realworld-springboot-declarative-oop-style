package io.github.juniqlim.realworld;

import org.springframework.stereotype.Service;

@Service
public class UpdateUser {
    private final UserRepository userRepository;

    public UpdateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User update(Request updateRequest) {
        User findedUser = userRepository.findByEmail(updateRequest.email());
        User updateUser = findedUser.update(updateRequest.bio(), updateRequest.image());
        userRepository.update(updateUser);
        return updateUser;
    }

    static class Request {
        private final String email;
        private final String bio;
        private final String image;

        public Request(String email, String bio, String image) {
            this.email = email;
            this.bio = bio;
            this.image = image;
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
