package io.github.juniqlim.realworld.user;

import io.github.juniqlim.realworld.Id;
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
        User followee = userRepository.findByUsername(username);
        follower.follow(followee.id());
        followee.addFollower(follower.id());
        return new Profile(followee, true);
    }

    public Profile unFollow(String jwsToken, String username) {
        User user = userRepository.findByToken(jwsToken);
        User followUser = userRepository.findByUsername(username);
        user.unfollow(followUser.id());
        return new Profile(followUser, false);
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
}
