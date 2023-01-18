package io.github.juniqlim.realworld.user.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import io.github.juniqlim.object.jwt.Jwt;
import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    void test() {
        assertThatCode(() -> new User(1, Jwt.FAKE.token(), "123", "Jacob", "jake@jake.jake")).doesNotThrowAnyException();
    }

    @Test
    void follow() {
        User jacob = new User(1, Jwt.FAKE.token(), "123", "Jacob", "jake@jake.jake");
        User juniq = new User(2, Jwt.FAKE.token(), "juniqjuniq", "juniq", "juniq@juniq.juniq");
        jacob.follow(juniq.id());
        juniq.addFollower(jacob.id());

        Profile juniqProfile = juniq.profile(jacob.id());
        assertThat(juniqProfile.isFollowing()).isTrue();
    }
}
