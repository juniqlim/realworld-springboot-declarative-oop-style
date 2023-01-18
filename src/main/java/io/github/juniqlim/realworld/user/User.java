package io.github.juniqlim.realworld.user;

import io.github.juniqlim.realworld.user.web.Token;

public interface User {
    io.github.juniqlim.realworld.user.domain.User.Id id();
    boolean isExist();
    boolean isNull();

    class UserByName implements User {
        private final FindUser findUser;
        private final String name;

        public UserByName(FindUser findUser, String userName) {
            this.findUser = findUser;
            this.name = userName;
        }

        public io.github.juniqlim.realworld.user.domain.User.Id id() {
            try {
                return findUser.findByUsername(name).id();
            } catch (IllegalArgumentException e) {
                return null;
            }
        }

        public boolean isExist() {
            try {
                findUser.findByUsername(name);
                return true;
            } catch (IllegalArgumentException e) {
                return false;
            }
        }

        public boolean isNull() {
            return name == null;
        }
    }

    class UserByToken implements User {
        private final FindUser findUser;
        private final Token token;

        public UserByToken(FindUser findUser, Token token) {
            this.findUser = findUser;
            this.token = token;
        }

        public io.github.juniqlim.realworld.user.domain.User.Id id() {
            if (!token.isExist()) {
                return null;
            }
            try {
                return findUser.find(token.value()).id();
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
        public boolean isExist() {
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

        public boolean isNull() {
            return token == null;
        }
    }

    class NoUser implements User {
        public io.github.juniqlim.realworld.user.domain.User.Id id() {
            return null;
        }
        public boolean isExist() {
            return false;
        }

        public boolean isNull() {
            return true;
        }
    }
}
