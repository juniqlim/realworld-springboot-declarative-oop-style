package io.github.juniqlim.realworld.user.web;

import io.github.juniqlim.object.jwt.VerifiedJwt;
import java.security.PublicKey;

public class Token {
    private final PublicKey publicKey;
    private final String value;

    public Token(PublicKey publicKey, String value) {
        this.publicKey = publicKey;
        this.value = value;
    }

    public String jwsToken() {
        String jwsToken = value.substring(6);
        if (!new VerifiedJwt.VerifiedJws(publicKey, jwsToken).verifiable()) {
            throw new IllegalArgumentException("Invalid jws");
        }
        return jwsToken;
    }
}
