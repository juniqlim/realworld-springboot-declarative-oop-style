package io.github.juniqlim.realworld.article;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.juniqlim.realworld.article.domain.Tag;
import io.github.juniqlim.realworld.article.repository.TagRepository;
import org.junit.jupiter.api.Test;

class TagUseCaseTest {
    @Test
    void test() {
        TagUseCase tagUseCase = new TagUseCase(new TagRepository());
        tagUseCase.merge("java");

        assertEquals(new Tag("java"), tagUseCase.findAll().get(0));

        tagUseCase.delete("java");
        assertEquals(0, tagUseCase.findAll().size());
    }
}