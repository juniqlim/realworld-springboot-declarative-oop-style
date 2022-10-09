package io.github.juniqlim.realworld;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CreateUserTest {
    @Test
    void test() {
        User user = new CreateUser().user(new CreateUser.Request("Jacob", "jake@jake.jake", "jakejake"));
        assertThat(user).isEqualTo(new User(Token.FAKE.token(), "123", "Jacob", "jake@jake.jake", "I work at statefarm", null));
    }
}
