package io.github.juniqlim.realworld.user.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import io.github.juniqlim.object.jwt.Jwt;
import io.github.juniqlim.realworld.user.domain.User.Id;
import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    void test() {
        assertThatCode(() -> new User(1, Jwt.FAKE.token(), "123", "Jacob", "jake@jake.jake")).doesNotThrowAnyException();
    }

    @Test
    void follow() {
        User jacob = new User(1, Jwt.FAKE.token(), "123", "Jacob", "jake@jake.jake");
        User.Id juniqId = new Id(2);
        jacob.follow(juniqId);

        Profile profileViewedByJuniq = jacob.profile(juniqId);
        assertThat(profileViewedByJuniq.isFollowing()).isTrue();
    }
}
