package io.github.juniqlim.realworld.article.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SlugTest {
    @Test
    void test() {
        assertEquals("how-to-train-your-dragon", new Slug("How to train your dragon").value());
    }
}