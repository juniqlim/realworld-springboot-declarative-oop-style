package io.github.juniqlim.realworld.user;

import io.github.juniqlim.realworld.user.domain.Profile;
import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class FindProfile {
    private final UserRepository userRepository;

    public FindProfile(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Profile profile(String jwsToken) {
        return new Profile(userRepository.findByToken(jwsToken), false);
    }
    public Profile profile(String jwsToken, String username) {
        User me = userRepository.findByToken(jwsToken);
        User user = userRepository.findByUsername(username);
        return new Profile(user, me.isFollow(user));
    }
}
