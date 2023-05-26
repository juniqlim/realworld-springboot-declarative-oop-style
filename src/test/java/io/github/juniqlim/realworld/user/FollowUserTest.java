package io.github.juniqlim.realworld.user;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class FollowUserTest {
    UserRepository repository;

    @BeforeEach
    void setUp() {
        repository = new UserRepository.Collection();
        repository.save(Fixture.JAKE);
        repository.save(Fixture.JUNIQ);
    }

    @Test
    void follow() {
        FollowUser followUser = new FollowUser(repository);
        assertThatCode(() -> followUser.follow(Fixture.JAKE.id(), Fixture.JUNIQ.id())).doesNotThrowAnyException();
    }

    @Test
    void unFollow() {
        FollowUser followUser = new FollowUser(repository);
        assertThatCode(() -> followUser.unFollow(Fixture.JAKE.id(), Fixture.JUNIQ.id())).doesNotThrowAnyException();
    }
}
