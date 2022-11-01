package io.github.juniqlim.realworld.user;

import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class FindUser {
    private final UserRepository userRepository;

    public FindUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User find(String jwsToken) {
        return userRepository.findByToken(jwsToken);
    }
}
