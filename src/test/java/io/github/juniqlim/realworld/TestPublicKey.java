package io.github.juniqlim.realworld;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class TestPublicKey {
    public PublicKey publicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String stringPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAh6ql6hhIxPChFZTXdDhVhjKhFF5BOTw9YwvoeP2CvtZq7atG+TFx+E6MucPVgns8RzRTQ+8dURtrYnR5KmzU4FGv/IN33oelbDd57kj5YDz0IjWUhYHgfVSXREv4oMBPKoz09E/3lOxtdC/WpRrYhoA7w+tudrw+Ah51CM4i1CQMXr1d44R1xyEsFXCl2tmfx9GEc25RIRgxVXhY0IzrKw7y6umG1gehhJgpEAtIFGKKhIkUJqjDpfw3kkHtcTzzKZDp6aZVtNJ5FW2pqURjhGu/I1KFd+9ARixdOR6s/E1Gk0Ak4TU6QOo41T5ZNbC66sbXl85vw1eePVmAkUtegQIDAQAB";
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(stringPublicKey)));
    }
}
