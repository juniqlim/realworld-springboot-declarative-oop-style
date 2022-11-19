package io.github.juniqlim.realworld.user.domain;

import io.github.juniqlim.realworld.user.domain.User.Id;

public class Profile {
    private final User.Id userId;
    private final String username;
    private final String bio;
    private final String image;
    private final boolean following;

    public Profile(User user, boolean following) {
        this(user.id(), user.username(), user.bio(), user.image(), following);
    }

    public Profile(Id userId, String username, String bio, String image, boolean following) {
        this.userId = userId;
        this.username = username;
        this.bio = bio;
        this.image = image;
        this.following = following;
    }

    public boolean equalsUserId(User.Id userId) {
        return this.userId.equals(userId);
    }

    public Id userId() {
        return userId;
    }

    public String username() {
        return username;
    }

    public String bio() {
        return bio;
    }

    public String image() {
        return image;
    }

    public boolean isFollowing() {
        return following;
    }
}
