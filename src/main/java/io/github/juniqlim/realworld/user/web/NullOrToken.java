package io.github.juniqlim.realworld.user.web;

import java.security.PublicKey;

public class NullOrToken {
    private final PublicKey publicKey;
    private final String value;

    public NullOrToken(PublicKey publicKey, String token) {
        this.publicKey = publicKey;
        this.value = token;
    }

    public String jwsToken() {
        if (value == null) {
            return null;
        }
        return new Token(publicKey, value).jwsToken();
    }
}
