package io.github.juniqlim.realworld.user.web;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import org.junit.jupiter.api.Test;

class Token2Test {
    @Test
    void test() throws NoSuchAlgorithmException, InvalidKeySpecException {
        assertFalse(new Token2.Jws(publicKey(), null).isExist());
        assertTrue(new Token2.Jws(publicKey(), "token eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzUxMiJ9.eyJleHAiOjMxNTM2MDAwLCJqdGkiOiJFV21hdGlQQkVsMkdHQlIzUlRJR213IiwiaWF0IjoxNjY3MzczOTEzfQ.fKYHVdJuaEt9j66a7l4-WEankDt67FaER-1l3G78vcX6PWMdTf4mj2euTjTRwyEfXjYdS4RailUVaxeDK_2wRjcRabaCEyz7SnipmeCU02rHM1tfuAC8YEa9jwXxyjx8q6Yxl8opVZ8RpUYJhHLp4zcNooljrGLEswYWJjhvl7BHPOydBw6T4XIb1fLoMwy7rAmhUHD8dxo483QLiZgxMVvRydRbJLboWLuzBJx-8KS-brYisxdE9Ot-M7j3k61g1gvWf-W3YjR6QulXkxcl0bRv4q7j1jHsBVjl8U5Y1OEVA9Lxll1Y0llVjkrpjv0L6uLT_S2OKdX98CMrrw4PU1").isExist());
    }

    PublicKey publicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String stringPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAh6ql6hhIxPChFZTXdDhVhjKhFF5BOTw9YwvoeP2CvtZq7atG+TFx+E6MucPVgns8RzRTQ+8dURtrYnR5KmzU4FGv/IN33oelbDd57kj5YDz0IjWUhYHgfVSXREv4oMBPKoz09E/3lOxtdC/WpRrYhoA7w+tudrw+Ah51CM4i1CQMXr1d44R1xyEsFXCl2tmfx9GEc25RIRgxVXhY0IzrKw7y6umG1gehhJgpEAtIFGKKhIkUJqjDpfw3kkHtcTzzKZDp6aZVtNJ5FW2pqURjhGu/I1KFd+9ARixdOR6s/E1Gk0Ak4TU6QOo41T5ZNbC66sbXl85vw1eePVmAkUtegQIDAQAB";
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(stringPublicKey)));
    }
}