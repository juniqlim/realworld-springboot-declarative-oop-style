package io.github.juniqlim.realworld.auth;

import io.github.juniqlim.realworld.Id;

public class HeaderAuthStringTo {
    public static Id userId(String headerAuthString) {
        if (headerAuthString == null) {
            return new Id.EmptyId();
        }
        return new Id.LongId(
            Long.parseLong(
                CachedAuthToken.id(token(headerAuthString))
            )
        );
    }

    static String token(String headerAuthString) {
        return headerAuthString.substring(6);
    }
}
