package io.github.juniqlim.realworld.user.web;

import io.github.juniqlim.object.jwt.VerifiedJwt;
import java.security.PublicKey;

public interface Token {
    String value();
    boolean isExist();
    void certify();

    class Jws implements Token {
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

        @Override
        public void certify() {
            if (value == null) {
                throw new IllegalArgumentException("Authorization token is not exist");
            }
            String jwsToken = value.substring(6);
            if (!new VerifiedJwt.VerifiedJws(publicKey, jwsToken).verifiable()) {
                throw new IllegalArgumentException("Invalid jws");
            }
        }
    }
}
