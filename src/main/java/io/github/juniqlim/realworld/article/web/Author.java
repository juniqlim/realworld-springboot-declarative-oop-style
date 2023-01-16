package io.github.juniqlim.realworld.article.web;

import io.github.juniqlim.realworld.user.domain.Profile;

public class Author {
    private final String username;
    private final String bio;
    private final String image;
    private final boolean following;

    Author(Profile profile) {
        this.username = profile.username();
        this.bio = profile.bio();
        this.image = profile.image();
        this.following = profile.isFollowing();
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