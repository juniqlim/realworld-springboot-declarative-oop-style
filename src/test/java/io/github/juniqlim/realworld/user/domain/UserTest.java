package io.github.juniqlim.realworld.user.domain;

import io.github.juniqlim.object.jwt.Jwt;
import io.github.juniqlim.realworld.user.domain.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class UserTest {
    @Test
    void test() {
        assertThatCode(() -> new User(Jwt.FAKE.token(), "123", "Jacob", "jake@jake.jake")).doesNotThrowAnyException();
    }
}
