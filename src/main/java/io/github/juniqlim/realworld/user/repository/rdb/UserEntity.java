package io.github.juniqlim.realworld.user.repository.rdb;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
class UserEntity {
    @Id
    private long id;
    private String email;
    @Column(nullable = false, length = 1000)
    private String token;
    private String username;
    private String password;
    private String bio;
    private String image;

    protected UserEntity() {
    }

    public UserEntity(long id, String email, String token, String username, String password, String bio, String image) {
        this.id = id;
        this.email = email;
        this.token = token;
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.image = image;
    }

    public long id() {
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

    public String password() {
        return password;
    }

    public String bio() {
        return bio;
    }

    public String image() {
        return image;
    }
}
