package io.github.juniqlim.realworld;

import org.springframework.stereotype.Service;

@Service
class UnfollowUser {
    private final UserRepository userRepository;

    public UnfollowUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Profile unfollow(String jwsToken, String username) {
        User user = userRepository.findByToken(jwsToken);
        User followUser = userRepository.findByUsername(username);
        user.unfollow(followUser.token());
        return new Profile(followUser, false);
    }
}
