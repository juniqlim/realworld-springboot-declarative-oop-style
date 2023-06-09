package io.github.juniqlim.realworld.user;

import io.github.juniqlim.realworld.user.domain.Profile;
import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FindProfileTest {
    UserRepository repository;
    User jake;

    @BeforeEach
    void setUp() {
        repository = new UserRepository.UserArrayListRepository();
        jake = new CreateUser(repository).user(new CreateUser.Request("Jacob", "jake@jake.jake", "jakejake"));
    }

    @Test
    void test() {
        Profile profile = new FindProfile(new FindUser(repository), new FollowUser(repository)).find(jake.id());
        assertThat(profile.username()).isEqualTo("Jacob");
    }
}