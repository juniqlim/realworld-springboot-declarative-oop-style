package io.github.juniqlim.realworld.user;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateUserTest {
    UserRepository repository;

    @BeforeEach
    void setUp() {
        repository = new UserRepository.Collection();
        repository.save(Fixture.JAKE);
    }

    @Test
    void test() {
        new UpdateUser(repository).update(
            new UpdateUser.Request(Fixture.JAKE.id(),"jake@jake.ee",null,null,
                "I like to skateboard",
                "https://i.stack.imgur.com/xHWG8.jpg")
        );
        User findedUser = repository.findByToken(Fixture.JAKE.token());
        assertEquals("jake@jake.ee", findedUser.email());
        assertEquals("I like to skateboard", findedUser.bio());
        assertEquals("https://i.stack.imgur.com/xHWG8.jpg", findedUser.image());
    }
}