package io.github.juniqlim.realworld.user.repository;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.juniqlim.object.jwt.Jwt;
import io.github.juniqlim.realworld.user.domain.User;
import org.junit.jupiter.api.Test;

public class UserRepositoryTest {
    @Test
    void save() {
        User user = new User(1, Jwt.FAKE.token(), "123", "Jacob", "jake@jake.jake");

        UserRepository userRepository = new UserRepository.Collection();
        userRepository.save(user);

        User findedUser = userRepository.findByToken(Jwt.FAKE.token());
        assertThat(findedUser).isEqualTo(user);
    }
}
