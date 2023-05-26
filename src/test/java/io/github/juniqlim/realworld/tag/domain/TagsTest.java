package io.github.juniqlim.realworld.tag.domain;

import io.github.juniqlim.realworld.Fixture;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TagsTest {
    @Test
    void test() {
        Tags tags = new Tags(
            Fixture.JAKE_ARTICLE.id(),
            Arrays.asList("tag")
        );
        assertEquals(
            Arrays.asList("tag"),
            tags.tags()
        );
    }
}