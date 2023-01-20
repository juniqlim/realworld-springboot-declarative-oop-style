package io.github.juniqlim.realworld.user.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class User {
    private final Id id;
    private final String token;
    private final String password;
    private final String username;
    private final String email;
    private final String bio;
    private final String image;
    private final Collection<User.Id> follows;
    private final Collection<User.Id> followers;

    public User(long id, String token, String password, String username, String email) {
        this(new Id(id), token, password, username, email, null, null);
    }

    public User(Id id, String token, String password, String username, String email, String bio, String image) {
        this.id = id;
        this.token = token;
        this.password = password;
        this.username = username;
        this.email = email;
        this.bio = bio;
        this.image = image;
        this.follows = new ArrayList<>();
        this.followers = new ArrayList<>();
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

    public String username() {
        return username;
    }

    public String bio() {
        return bio;
    }

    public String image() {
        return image;
    }

    public boolean equalsId(User.Id id) {
        return this.id.equals(id);
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

    public User update(UpdateRequest updateRequest) {
        RefinedUpdateRequest request = new RefinedUpdateRequest(updateRequest);
        return new User(id, token, request.password(), request.username(), request.email(), request.bio(), request.image());
    }

    public void follow(Id userId) {
        follows.add(userId);
    }

    public boolean isFollow(Id userId) {
        return follows.contains(userId);
    }

    public void unfollow(Id userId) {
        follows.remove(userId);
    }

    public Collection<User.Id> follows() {
        return follows;
    }

    public void addFollower(Id userId) {
        followers.add(userId);
    }

    public void removeFollower(Id userId) {
        followers.remove(userId);
    }

    public Collection<User.Id> followers() {
        return followers;
    }

    public Profile profile() {
        return new Profile(id, username, bio, image, false);
    }

    public Profile profile(User.Id selecterId) {
        return new Profile(id, username, bio, image, followers.contains(selecterId));
    }

    public static class Id {
        private final long value;

        public Id(long value) {
            this.value = value;
        }

        public long value() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Id id = (Id) o;
            return value == id.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    public interface UpdateRequest {
        String email();
        String password();
        String username();
        String bio();
        String image();
    }

    private class RefinedUpdateRequest {
        private final UpdateRequest updateRequest;

        public RefinedUpdateRequest(UpdateRequest updateRequest) {
            this.updateRequest = updateRequest;
        }

        public String email() {
            return updateRequest.email() == null || updateRequest.email().isEmpty() ? email : updateRequest.email();
        }

        public String password() {
            return updateRequest.password() == null || updateRequest.password().isEmpty() ? password : updateRequest.password();
        }

        public String username() {
            return updateRequest.username() == null || updateRequest.username().isEmpty() ? username : updateRequest.username();
        }

        public String bio() {
            return updateRequest.bio() == null || updateRequest.bio().isEmpty() ? bio : updateRequest.bio();
        }

        public String image() {
            return updateRequest.image() == null || updateRequest.image().isEmpty() ? image : updateRequest.image();
        }
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
