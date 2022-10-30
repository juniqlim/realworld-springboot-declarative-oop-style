package io.github.juniqlim.realworld;

import org.springframework.stereotype.Service;

@Service
public class FollowUser {
    private final UserRepository userRepository;

    public FollowUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Profile follow(String jwsToken, String username) {
        User user = userRepository.findByToken(jwsToken);
        User followUser = userRepository.findByUsername(username);
        user.follow(followUser.token());
        return new Profile(followUser, true);
    }
}
