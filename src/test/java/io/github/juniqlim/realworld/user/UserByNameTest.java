package io.github.juniqlim.realworld.user;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.github.juniqlim.realworld.user.repository.UserRepository.Collection;
import org.junit.jupiter.api.Test;

class UserByNameTest {
    @Test
    void test() {
        FindUser findUser = new FindUser(new Collection());
        assertFalse(new UserByName(findUser, "juniqlim").isExist());
        assertNull(new UserByName(findUser, "juniqlim").id());
    }
}