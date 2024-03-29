package io.github.juniqlim.realworld.user.domain;

import io.github.juniqlim.realworld.Fixture;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class UserTest {
    @Test
    void test() {
        assertThatCode(() -> new User(Fixture.LONG_ID_ONE, Fixture.JAKE.token(), "123", "Jacob", "jake@jake.jake")).doesNotThrowAnyException();
    }
}
