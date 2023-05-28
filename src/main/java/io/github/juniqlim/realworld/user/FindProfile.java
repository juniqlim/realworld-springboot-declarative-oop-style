package io.github.juniqlim.realworld.user;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.user.domain.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        return new Profile(findUser.find(findingUserId), followUser.isFollowing(loginUserId, findingUserId));
    }

    public Profile find(Id findingUserId) {
        return new Profile(findUser.find(findingUserId), false);
    }

    public List<Profile> find(List<Id> findingUserIds, Id loginUserId) {
        if (loginUserId.isEmpty()) {
            return findUser.findList(findingUserIds).stream()
                .map(user -> new Profile(user, false))
                .collect(Collectors.toList());
        }
        return findUser.findList(findingUserIds).stream()
            .map(user ->
                new Profile(
                    user,
                    followUser.followingUserIds(loginUserId)
                        .contains(user.id())
                )
            )
            .collect(Collectors.toList());
    }
}
