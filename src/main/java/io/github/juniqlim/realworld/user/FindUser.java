package io.github.juniqlim.realworld.user;

import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.domain.User.Id;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import io.github.juniqlim.realworld.user.web.Token;
import java.util.List;
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

    public User find(Token token) {
        return userRepository.findByToken(token.value());
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User find(User.Id id) {
        return userRepository.findById(id);
    }

    public List<User> findList(List<Id> ids) {
        return userRepository.findByIds(ids);
    }
}
