package io.github.juniqlim.realworld.user;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.juniqlim.realworld.user.domain.Profile;
import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FindProfileTest {
    UserRepository repository;
    User jake;

    @BeforeEach
    void setUp() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String testPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCHqqXqGEjE8KEVlNd0OFWGMqEUXkE5PD1jC+h4/YK+1mrtq0b5MXH4Toy5w9WCezxHNFND7x1RG2tidHkqbNTgUa/8g3feh6VsN3nuSPlgPPQiNZSFgeB9VJdES/igwE8qjPT0T/eU7G10L9alGtiGgDvD6252vD4CHnUIziLUJAxevV3jhHXHISwVcKXa2Z/H0YRzblEhGDFVeFjQjOsrDvLq6YbWB6GEmCkQC0gUYoqEiRQmqMOl/DeSQe1xPPMpkOnpplW00nkVbampRGOEa78jUoV370BGLF05Hqz8TUaTQCThNTpA6jjVPlk1sLrqxteXzm/DV549WYCRS16BAgMBAAECggEATnXEyqQMwckggCi6ij5iR+BUBEGWmxFZt0SVyBNlYBUFdjqOK2QLM73J6U1216WJ8Ow0E5/gZp3i9ufBg2W5n5nem700v//XDcTvwX12q8/UqUwvfx7jw9f+O8NsIRvXLRakO/9bgkdR7dYU3xutievzpJUuJ4Eqo3MV4GfHAMSYvTdwEcJezfD6JuBdvoAW4wdt0LU6VPy888EHNPdWuRE/AuUIAhqfvyCLITaZY6RwYhjzYu6MK3R25rvu/rcHplZ78ukeR+OXNKO+NRg9vF2Lxjp2ZqGa4/CsNvzi2EO5lR1ITfqbza20iPppKC4/gtVXIfdWgn0rWt3nWVTRuQKBgQC+o9pr/ch8vmUKiTeSdt+/YnZEoL3Y5CMzqdbnZvrTCUbuhQ6uzNiibi4kOneP+igilmgYnHd/4VWFTU5ymPvvzbW3s5q2a4SOswLBMgOgW04pbl59Bp4jPsmbkX2zAi0r3y90MDMCPSz34lelo7hnOniCsmLSb3SKq1KYp2SnlwKBgQC2Ld6uWMGhCZnVEEByfBOPdXTbE1WF9hj6XvvBJzoFU0pObHJc5eUiAj5zwSCAOsV2aF84RmFOnZEUkBD7+1lTdIIlYdG+YEv2g3dwMZ0Pesr+JIupcX4POekcdhzWVXPinD5rpA+zp/qs7mxoaxVAMEesEkP2veP7idv85cetpwKBgQCFZV4XJrO9JfTJ1I5APFIAN0OObfOp6qj/I56uuQ5V0S4DBLPreIc10WcUDp1O77pZyWj5n7K2ltQivJ9h7M6NVCINcu2VK9LLj1MkIH0NHhapQwN29MR+4RZtdva/5Yv6IOexo3Bt5qSqp4Sw8Mi94tokifJAUaD4zzyyeJ8j7QKBgBo9wTWb00g++cmW7bGP7cFcSdjEkC0bpb1qsRjBbboWpT1moZKACuE7MYO67dOKo6bKoeyZNqr8R0mO9uNU8Sj0P0rjLDMf69E2Xp3qh5UTOuogmX1uu5m13b4bxUuaQ3cAynz2xMw8Bf+i2DqyMf1s9uXyO5fvAkfZM7bZ4klBAoGAXn5MIgPlFF+XfgDGOODliNNTsVbubL9ByOQIUZF18NRjqr9qzYRYwlacUExLC6Bv/giApAMUDqPlrt5hUx9y6JOHifvOVAp5BMn6mcL6tcLTNgemtSkc8wxEk1lpTkeOh/KflxZc3P0pJQAhksAT8PvD4fD//4D7bsavLfXaSm0=";
        PrivateKey privateKey = KeyFactory.getInstance("RSA")
            .generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(testPrivateKey)));

        repository = new UserRepository.Collection();
        jake = new CreateUser(repository, privateKey).user(new CreateUser.Request("Jacob", "jake@jake.jake", "jakejake"));
    }

    @Test
    void test() {
        Profile profile = new FindProfile(repository).profile(jake.token());
        assertThat(profile.username()).isEqualTo("Jacob");
    }
}