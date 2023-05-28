package io.github.juniqlim.realworld.user.web;

import io.github.juniqlim.realworld.Id;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CachedAuthTokenTest {
    @Test
    void create() {
        String token = CachedAuthToken.authToken().value(10984);
        Id id = CachedAuthToken.authToken().id(token);
        assertEquals(10984L, ((Id.LongId)id).value());
    }

    @Test
    void invalid() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
        assertThrows(IllegalArgumentException.class, () -> CachedAuthToken.authToken().id(token), "Invalid auth token");
    }
}