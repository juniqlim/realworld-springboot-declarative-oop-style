package io.github.juniqlim.realworld.user;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.user.domain.Profile;
import org.springframework.stereotype.Service;

@Service
public class FindProfile {
    private final FindUser findUser;
    private final FollowUser followUser;

    public FindProfile(FindUser findUser, FollowUser followUser) {
        this.findUser = findUser;
        this.followUser = followUser;
    }

    public Profile find(Id findingUserId, Id loginUserId) {
        if (loginUserId.isEmpty()) {
            return find(findingUserId);
        }
        return findUser.find(findingUserId).profile(followUser.isFollowing(loginUserId, findingUserId));
    }

    public Profile find(Id findingUserId) {
        return findUser.find(findingUserId).profile();
    }
}
