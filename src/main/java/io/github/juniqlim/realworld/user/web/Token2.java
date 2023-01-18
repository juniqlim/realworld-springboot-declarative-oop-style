package io.github.juniqlim.realworld.user.web;

import io.github.juniqlim.object.jwt.VerifiedJwt;
import java.security.PublicKey;

public interface Token2 {
    String value();
    boolean isExist();

    class Jws implements Token2 {
        private final PublicKey publicKey;
        private final String value;

        public Jws(PublicKey publicKey, String value) {
            this.publicKey = publicKey;
            this.value = value;
        }

        @Override
        public String value() {
            String jwsToken = value.substring(6);
            if (!new VerifiedJwt.VerifiedJws(publicKey, jwsToken).verifiable()) {
                throw new IllegalArgumentException("Invalid jws");
            }
            return jwsToken;
        }

        @Override
        public boolean isExist() {
            return value != null;
        }
    }
}
