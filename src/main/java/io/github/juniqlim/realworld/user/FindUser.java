package io.github.juniqlim.realworld.user;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindUser {
    private final UserRepository userRepository;

    public FindUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User find(String jwsToken) {
        return userRepository.findByToken(jwsToken);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User find(Id id) {
        return userRepository.findById(id);
    }

    public List<User> findList(List<Id> ids) {
        return userRepository.findByIds(ids);
    }

    public Id findIdByUsername(String username) {
        try {
            return findByUsername(username).id();
        } catch (IllegalArgumentException e) {
            return new Id.EmptyId();
        }
    }
}
