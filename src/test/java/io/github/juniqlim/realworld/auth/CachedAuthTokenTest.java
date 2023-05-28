package io.github.juniqlim.realworld.auth;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CachedAuthTokenTest {
    @Test
    void create() {
        String token = CachedAuthToken.token(10984);
        String id = CachedAuthToken.id(token);
        assertEquals("10984", id);
    }

    @Test
    void invalid() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
        assertThrows(IllegalArgumentException.class, () -> HeaderAuthStringTo.userId(token), "Invalid auth token");
    }
}