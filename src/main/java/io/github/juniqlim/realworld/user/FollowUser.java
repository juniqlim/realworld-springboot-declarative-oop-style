package io.github.juniqlim.realworld.user;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowUser {
    private final UserRepository userRepository;

    public FollowUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void follow(Id followerUserId, Id followeeUserId) {
        userRepository.follow(followerUserId, followeeUserId);
    }

    public void unFollow(Id followerUserId, Id followeeUserId) {
        userRepository.unFollow(followerUserId, followeeUserId);
    }

    public boolean isFollowing(Id followerUserId, Id followeeUserId) {
        return userRepository.isFollowing(followerUserId, followeeUserId);
    }

    public List<Id> followingUserIds(Id followerUserId) {
        return userRepository.followingUserIds(followerUserId);
    }
}
