package io.github.juniqlim.realworld.user;

import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindUserTest {
    UserRepository repository;
    String jwsToken;

    @BeforeEach
    void setUp() {
        repository = new UserRepository.UserArrayListRepository();
        jwsToken = new CreateUser(repository).user(new CreateUser.Request("Jacob", "jake@jake.jake", "jakejake")).token();
    }

    @Test
    void test() {
        User user = new FindUser(repository).find(jwsToken);
        assertEquals("Jacob", user.username());
    }
}
