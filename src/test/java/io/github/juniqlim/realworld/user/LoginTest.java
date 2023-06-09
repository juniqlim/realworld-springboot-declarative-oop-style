package io.github.juniqlim.realworld.user;

import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginTest {
    UserRepository repository;

    @BeforeEach
    void setUp() {
        repository = new UserRepository.UserArrayListRepository();
        new CreateUser(repository).user(new CreateUser.Request("Jacob", "jake@jake.jake", "jakejake"));
    }

    @Test
    void test() {
        User user = new LoginUser(repository).login(new LoginUser.Request("jake@jake.jake", "jakejake"));
        assertEquals("Jacob", user.username());
    }
}
