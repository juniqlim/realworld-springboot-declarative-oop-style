package io.github.juniqlim.realworld.user;

import io.github.juniqlim.realworld.user.domain.Profile;
import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.domain.User.Id;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
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
        return new Profile(user, me.isFollow(user.id()));
    }

    public Profile profile(String jwsToken, User.Id userId) {
        User me = userRepository.findByToken(jwsToken);
        User user = userRepository.findById(userId);
        return new Profile(user, me.isFollow(user.id()));
    }

    public List<Profile> profiles(String jwsToken, List<User.Id> userId) {
        User me = userRepository.findByToken(jwsToken);
        return getProfiles(userId, me);
    }

    private List<Profile> getProfiles(List<Id> userId, User me) {
        List<User> users = userRepository.findByIds(userId);
        return users.stream()
            .map(user -> new Profile(user, me.isFollow(user.id())))
            .collect(Collectors.toList());
    }

    public Profile profile(User.Id userId) {
        return new Profile(userRepository.findById(userId), false);
    }

    public List<Profile> profiles(List<User.Id> userIds) {
        List<User> users = userRepository.findByIds(userIds);
        return users.stream()
            .map(user -> new Profile(user, false))
            .collect(Collectors.toList());
    }
}
