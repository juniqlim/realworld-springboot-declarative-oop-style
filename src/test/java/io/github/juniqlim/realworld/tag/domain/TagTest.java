package io.github.juniqlim.realworld.tag.domain;

import io.github.juniqlim.realworld.Fixture;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TagTest {
    @Test
    void test() {
        Tag tag = new Tag("tag", Arrays.asList(Fixture.LONG_ID_ONE, Fixture.LONG_ID_THREE));
        assertEquals("tag", tag.value());
    }
}