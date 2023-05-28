package io.github.juniqlim.realworld.auth;

import io.github.juniqlim.object.jwt.DecodedJws;
import io.github.juniqlim.object.jwt.Jws;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;

public class JwsToken {
    private final Jws jws;
    private final DecodedJws decodedJws;

    public JwsToken(PrivateKey privateKey, PublicKey publicKey) {
        this.jws = new Jws.RsaJws(privateKey);
        this.decodedJws = new DecodedJws.DecodedRsaJws(publicKey);
    }

    public String token(Map<String, Object> payload) {
        return jws.token(payload);
    }

    public String payload(String token) {
        try {
            return decodedJws.payload(token);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid auth token");
        }
    }
}