package io.github.juniqlim.realworld.article.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SlugifyTest {
    @Test
    void test() {
        assertEquals("how-to-train-your-dragon", new Slugify().withDash("How to train your dragon"));
    }
}