package io.github.juniqlim.realworld.user;

import io.github.juniqlim.realworld.user.domain.Profile;
import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class FollowUser {
    private final UserRepository userRepository;

    public FollowUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Profile follow(String jwsToken, String username) {
        User follower = userRepository.findByToken(jwsToken);
        User following = userRepository.findByUsername(username);
        follower.follow(following.id());
        following.addFollower(follower.id());
        return new Profile(following, true);
    }
}
