package io.github.juniqlim.realworld.user.repository;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.user.domain.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRepositoryTest {
    @Test
    void save() {
        User user = new User(Fixture.LONG_ID_ONE, Fixture.JAKE.token(), "123", "Jacob", "jake@jake.jake");

        UserRepository userRepository = new UserRepository.Collection();
        userRepository.save(user);

        User findedUser = userRepository.findByToken(Fixture.JAKE.token());
        assertThat(findedUser).isEqualTo(user);
    }
}
