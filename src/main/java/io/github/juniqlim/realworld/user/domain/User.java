package io.github.juniqlim.realworld.user.domain;

import io.github.juniqlim.realworld.Id;

import java.util.Objects;

public class User {
    private final Id id;
    private final String token;
    private final String password;
    private final String username;
    private final String email;
    private final String bio;
    private final String image;

    public User(Id id, String token, String password, String username, String email) {
        this(id, token, password, username, email, null, null);
    }

    public User(Id id, String token, String password, String username, String email, String bio, String image) {
        this.id = id;
        this.token = token;
        this.password = password;
        this.username = username;
        this.email = email;
        this.bio = bio;
        this.image = image;
    }

    public User updatePassword(String password) {
        return new User(id, token, password, username, email, bio, image);
    }

    public User updateUsername(String username) {
        return new User(id, token, password, username, email, bio, image);
    }

    public User updateEmail(String email) {
        return new User(id, token, password, username, email, bio, image);
    }

    public User updateBio(String bio) {
        return new User(id, token, password, username, email, bio, image);
    }

    public User updateImage(String image) {
        return new User(id, token, password, username, email, bio, image);
    }

    public User updateToken(String token) {
        return new User(id, token, password, username, email, bio, image);
    }

    public Profile profile() {
        return new Profile(id, username, bio, image, false);
    }

    public Profile profile(boolean isFollowing) {
        return new Profile(id, username, bio, image, isFollowing);
    }

    public Id id() {
        return id;
    }

    public String email() {
        return email;
    }

    public String token() {
        return token;
    }

    public String password() {
        return password;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(token, user.token) && Objects.equals(password, user.password) && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(bio, user.bio) && Objects.equals(image, user.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, password, username, email, bio, image);
    }
}
