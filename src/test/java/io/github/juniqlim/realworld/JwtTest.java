package io.github.juniqlim.realworld;

import org.junit.jupiter.api.Test;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class JwtTest {
    @Test
    void test() throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(1024);

        String token = new Jwt(kpg.generateKeyPair()).token();
        System.out.println("token = " + token);
    }
}
