package io.github.juniqlim.realworld.user.repository.rdb;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.user.domain.User;

class UserToUserEntity {
    UserEntity userEntity(User user) {
        return new UserEntity(
                user.id().value(),
                user.email(),
                user.token(),
                user.username(),
                user.password(),
                user.bio(),
                user.image()
        );
    }

    User user(UserEntity user) {
        return new User(
                new Id.LongId(user.id()),
                user.token(),
                user.password(),
                user.username(),
                user.email(),
                user.bio(),
                user.image()
        );
    }
}
