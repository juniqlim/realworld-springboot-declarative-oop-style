package io.github.juniqlim.realworld;

public class Profile {
    private final String username;
    private final String bio;
    private final String image;
    private final boolean following;

    public Profile(User user, boolean following) {
        this.username = user.username();
        this.bio = user.bio();
        this.image = user.image();
        this.following = following;
    }

    public Profile(String username, String bio, String image, boolean following) {
        this.username = username;
        this.bio = bio;
        this.image = image;
        this.following = following;
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