package io.github.juniqlim.realworld.auth;

import com.jayway.jsonpath.JsonPath;
import io.juniqlim.objects.encryption.Encryption;

import java.util.HashMap;

class AuthToken {
    private final JwsToken jwsToken;
    private final Encryption encryption;

    AuthToken(JwsToken jwsToken, Encryption encryption) {
        this.jwsToken = jwsToken;
        this.encryption = encryption;
    }

    String token(long userId) {
        return jwsToken.token(
            new HashMap<String, Object>() {{
                put("iat", System.currentTimeMillis() / 1000L);
                put("exp", 60 * 60 * 24);
                put("id", encryption.encrypt(String.valueOf(userId)));
            }}
        );
    }

    String id(String token) {
        return encryption.decrypt(
            JsonPath.parse(jwsToken.payload(token)).read("$.id", String.class)
        );
    }
}
