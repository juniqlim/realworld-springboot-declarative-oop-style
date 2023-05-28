package io.github.juniqlim.realworld.auth;

import io.juniqlim.objects.encryption.AESEncryption;

public class CachedAuthToken {
    private static final AuthToken authToken;

    static {
        String stringPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCHqqXqGEjE8KEVlNd0OFWGMqEUXkE5PD1jC+h4/YK+1mrtq0b5MXH4Toy5w9WCezxHNFND7x1RG2tidHkqbNTgUa/8g3feh6VsN3nuSPlgPPQiNZSFgeB9VJdES/igwE8qjPT0T/eU7G10L9alGtiGgDvD6252vD4CHnUIziLUJAxevV3jhHXHISwVcKXa2Z/H0YRzblEhGDFVeFjQjOsrDvLq6YbWB6GEmCkQC0gUYoqEiRQmqMOl/DeSQe1xPPMpkOnpplW00nkVbampRGOEa78jUoV370BGLF05Hqz8TUaTQCThNTpA6jjVPlk1sLrqxteXzm/DV549WYCRS16BAgMBAAECggEATnXEyqQMwckggCi6ij5iR+BUBEGWmxFZt0SVyBNlYBUFdjqOK2QLM73J6U1216WJ8Ow0E5/gZp3i9ufBg2W5n5nem700v//XDcTvwX12q8/UqUwvfx7jw9f+O8NsIRvXLRakO/9bgkdR7dYU3xutievzpJUuJ4Eqo3MV4GfHAMSYvTdwEcJezfD6JuBdvoAW4wdt0LU6VPy888EHNPdWuRE/AuUIAhqfvyCLITaZY6RwYhjzYu6MK3R25rvu/rcHplZ78ukeR+OXNKO+NRg9vF2Lxjp2ZqGa4/CsNvzi2EO5lR1ITfqbza20iPppKC4/gtVXIfdWgn0rWt3nWVTRuQKBgQC+o9pr/ch8vmUKiTeSdt+/YnZEoL3Y5CMzqdbnZvrTCUbuhQ6uzNiibi4kOneP+igilmgYnHd/4VWFTU5ymPvvzbW3s5q2a4SOswLBMgOgW04pbl59Bp4jPsmbkX2zAi0r3y90MDMCPSz34lelo7hnOniCsmLSb3SKq1KYp2SnlwKBgQC2Ld6uWMGhCZnVEEByfBOPdXTbE1WF9hj6XvvBJzoFU0pObHJc5eUiAj5zwSCAOsV2aF84RmFOnZEUkBD7+1lTdIIlYdG+YEv2g3dwMZ0Pesr+JIupcX4POekcdhzWVXPinD5rpA+zp/qs7mxoaxVAMEesEkP2veP7idv85cetpwKBgQCFZV4XJrO9JfTJ1I5APFIAN0OObfOp6qj/I56uuQ5V0S4DBLPreIc10WcUDp1O77pZyWj5n7K2ltQivJ9h7M6NVCINcu2VK9LLj1MkIH0NHhapQwN29MR+4RZtdva/5Yv6IOexo3Bt5qSqp4Sw8Mi94tokifJAUaD4zzyyeJ8j7QKBgBo9wTWb00g++cmW7bGP7cFcSdjEkC0bpb1qsRjBbboWpT1moZKACuE7MYO67dOKo6bKoeyZNqr8R0mO9uNU8Sj0P0rjLDMf69E2Xp3qh5UTOuogmX1uu5m13b4bxUuaQ3cAynz2xMw8Bf+i2DqyMf1s9uXyO5fvAkfZM7bZ4klBAoGAXn5MIgPlFF+XfgDGOODliNNTsVbubL9ByOQIUZF18NRjqr9qzYRYwlacUExLC6Bv/giApAMUDqPlrt5hUx9y6JOHifvOVAp5BMn6mcL6tcLTNgemtSkc8wxEk1lpTkeOh/KflxZc3P0pJQAhksAT8PvD4fD//4D7bsavLfXaSm0=";
        String stringPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAh6ql6hhIxPChFZTXdDhVhjKhFF5BOTw9YwvoeP2CvtZq7atG+TFx+E6MucPVgns8RzRTQ+8dURtrYnR5KmzU4FGv/IN33oelbDd57kj5YDz0IjWUhYHgfVSXREv4oMBPKoz09E/3lOxtdC/WpRrYhoA7w+tudrw+Ah51CM4i1CQMXr1d44R1xyEsFXCl2tmfx9GEc25RIRgxVXhY0IzrKw7y6umG1gehhJgpEAtIFGKKhIkUJqjDpfw3kkHtcTzzKZDp6aZVtNJ5FW2pqURjhGu/I1KFd+9ARixdOR6s/E1Gk0Ak4TU6QOo41T5ZNbC66sbXl85vw1eePVmAkUtegQIDAQAB";
        String secretKey = "23293492310348120948212084251124";
        authToken = new AuthToken(
            new JwsToken(
                new Key().privateKey(stringPrivateKey),
                new Key().publicKey(stringPublicKey)
            ),
            new AESEncryption(secretKey)
        );
    }

    public static AuthToken authToken() {
        return authToken;
    }
}
