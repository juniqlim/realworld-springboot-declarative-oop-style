package io.github.juniqlim.realworld.tag.domain;

import io.github.juniqlim.realworld.Fixture;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TagsTest {
    @Test
    void test() {
        Tags tags = new Tags(
            Fixture.JAKE_ARTICLE.id(),
            Collections.singletonList("tag")
        );
        assertEquals(
            Collections.singletonList("tag"),
            tags.tags()
        );
    }
}