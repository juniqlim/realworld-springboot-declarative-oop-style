package io.github.juniqlim.realworld.user;

import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CreateUserTest {
    @Test
    void saveAndFind() {
        UserRepository userRepository = new UserRepository.Collection();

        User user = new CreateUser(userRepository).user(new CreateUser.Request("Jacob", "jake@jake.jake", "jakejake"));
        User findedUser = userRepository.findByToken(user.token());

        assertThat(user).isEqualTo(findedUser);
    }
}
