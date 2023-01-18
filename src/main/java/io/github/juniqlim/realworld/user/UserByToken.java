package io.github.juniqlim.realworld.user;

import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.web.Token2;

public class UserByToken {
    private final FindUser findUser;
    private final Token2 token;

    public UserByToken(FindUser findUser, Token2 token) {
        this.findUser = findUser;
        this.token = token;
    }

    boolean isExist() {
        if (!token.isExist()) {
            return false;
        }
        try {
            findUser.find(token.value());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    User.Id id() {
        if (!token.isExist()) {
            return null;
        }
        try {
            return findUser.find(token.value()).id();
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
