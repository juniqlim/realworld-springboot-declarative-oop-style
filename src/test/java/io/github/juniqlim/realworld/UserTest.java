package io.github.juniqlim.realworld;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class UserTest {
    @Test
    void test() {
        assertThatCode(() -> new User(Token.FAKE.token(), "123", "Jacob", "jake@jake.jake")).doesNotThrowAnyException();
    }
}
