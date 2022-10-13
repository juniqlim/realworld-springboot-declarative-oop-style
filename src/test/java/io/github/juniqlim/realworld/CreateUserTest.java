package io.github.juniqlim.realworld;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CreateUserTest {
    @Test
    void saveAndFind() {
        UserRepository userRepository = new UserRepository();
        String token = Token.FAKE.token();

        User user = new CreateUser(userRepository, Token.FAKE).user(new CreateUser.Request("Jacob", "jake@jake.jake", "jakejake"));
        User findedUser = userRepository.findByToken(token);

        assertThat(user).isEqualTo(findedUser);
    }
}
