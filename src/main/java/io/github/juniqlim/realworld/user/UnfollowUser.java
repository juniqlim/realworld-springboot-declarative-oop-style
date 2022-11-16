package io.github.juniqlim.realworld.user;

import io.github.juniqlim.realworld.user.domain.Profile;
import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UnfollowUser {
    private final UserRepository userRepository;

    public UnfollowUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Profile unfollow(String jwsToken, String username) {
        User user = userRepository.findByToken(jwsToken);
        User followUser = userRepository.findByUsername(username);
        user.unfollow(followUser.id());
        return new Profile(followUser, false);
    }
}
