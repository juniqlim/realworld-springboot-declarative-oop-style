package io.github.juniqlim.realworld;

import java.util.Objects;

public class User {
    private final String email;
    private final String token;
    private final String username;
    private final String bio;
    private final String image;

    public User(String email, String token, String username, String bio, String image) {
        this.email = email;
        this.token = token;
        this.username = username;
        this.bio = bio;
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(token, user.token) && Objects.equals(username, user.username) && Objects.equals(bio, user.bio) && Objects.equals(image, user.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, token, username, bio, image);
    }
}
