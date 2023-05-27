package io.github.juniqlim.realworld.user.repository.rdb;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import(UserRDBRepository.class)
class UserRDBRepositoryIT {
    @Autowired UserRDBRepository userRDBRepository;

    @Test
    void test() {
        userRDBRepository.save(Fixture.JAKE);
        assertEquals(Fixture.JAKE.id(), userRDBRepository.findByToken(Fixture.JAKE.token()).id());

        userRDBRepository.update(Fixture.JAKE.updateEmail("a@a.a"));
        User updatedUser = userRDBRepository.findByToken(Fixture.JAKE.token());
        assertEquals("a@a.a", updatedUser.email());
        assertEquals(Fixture.JAKE.username(), updatedUser.username());
    }
}