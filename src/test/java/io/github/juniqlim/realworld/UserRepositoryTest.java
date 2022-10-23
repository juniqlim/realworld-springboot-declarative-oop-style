package io.github.juniqlim.realworld;

import io.github.juniqlim.object.jwt.Jwt;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRepositoryTest {
    @Test
    void save() {
        User user = new User(Jwt.FAKE.token(), "123", "Jacob", "jake@jake.jake");

        UserRepository userRepository = new UserRepository();
        userRepository.save(user);

        User findedUser = userRepository.findByToken(Jwt.FAKE.token());
        assertThat(findedUser).isEqualTo(user);
    }
}
