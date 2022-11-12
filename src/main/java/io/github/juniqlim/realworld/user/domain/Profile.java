package io.github.juniqlim.realworld.user.domain;

public class Profile {
    private final User.Id userId;
    private final String username;
    private final String bio;
    private final String image;
    private final boolean following;

    public Profile(User user, boolean following) {
        this.userId = user.id();
        this.username = user.username();
        this.bio = user.bio();
        this.image = user.image();
        this.following = following;
    }

    public boolean equalsUserId(User.Id userId) {
        return this.userId.equals(userId);
    }

    public String getUsername() {
        return username;
    }

    public String getBio() {
        return bio;
    }

    public String getImage() {
        return image;
    }

    public boolean isFollowing() {
        return following;
    }
}
