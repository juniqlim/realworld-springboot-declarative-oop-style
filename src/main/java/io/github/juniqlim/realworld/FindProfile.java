package io.github.juniqlim.realworld;

import org.springframework.stereotype.Service;

@Service
class FindProfile {
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
