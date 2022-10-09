package io.github.juniqlim.realworld;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.Date;

public interface Token {
    String token();

    @Component
    class Jwt implements Token {
        private final KeyPair keyPair;

        public Jwt(KeyPair keyPair) {
            this.keyPair = keyPair;
        }

        public String token() {
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
            return JWT.create()
                    .withIssuedAt(Date.from(Instant.now()))
                    .sign(algorithm);
        }
    }

    Token FAKE = () -> "fake.token";
}
