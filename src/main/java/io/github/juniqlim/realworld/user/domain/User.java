package io.github.juniqlim.realworld.user.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class User {
    private final String token;
    private final String password;
    private final String username;
    private final String email;
    private final String bio;
    private final String image;
    private final Collection<String> follows;

    public User(String token, String password, String username, String email) {
        this(token, password, username, email, null, null);
    }

    public User(String token, String password, String username, String email, String bio, String image) {
        this.token = token;
        this.password = password;
        this.username = username;
        this.email = email;
        this.bio = bio;
        this.image = image;
        this.follows = new ArrayList<>();
    }

    public String email() {
        return email;
    }

    public String token() {
        return token;
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

    public boolean equalsToken(String token) {
        return this.token.equals(token);
    }

    public boolean equalsEmail(String email) {
        return this.email.equals(email);
    }

    public boolean equalsPassword(String password) {
        return this.password.equals(password);
    }

    public boolean equalsUsername(String username) {
        return this.username.equals(username);
    }

    public User update(String email, String password, String username, String bio, String image) {
        return new User(token, password, username, email, bio, image);
    }

    public void follow(String jwsToken) {
        follows.add(jwsToken);
    }

    public boolean isFollow(User user) {
        return follows.contains(user.token);
    }

    public void unfollow(String jwsToken) {
        follows.remove(jwsToken);
    }
}
