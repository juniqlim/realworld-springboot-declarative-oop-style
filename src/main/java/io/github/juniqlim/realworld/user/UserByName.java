package io.github.juniqlim.realworld.user;

import io.github.juniqlim.realworld.user.domain.User;

class UserByName {
    private final FindUser findUser;
    private final String name;

    public UserByName(FindUser findUser, String userName) {
        this.findUser = findUser;
        this.name = userName;
    }

    boolean isExist() {
        try {
            findUser.findByUsername(name);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    User.Id id() {
        try {
            return findUser.findByUsername(name).id();
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
