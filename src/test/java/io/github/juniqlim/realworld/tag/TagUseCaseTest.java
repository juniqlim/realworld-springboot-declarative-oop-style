package io.github.juniqlim.realworld.tag;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.tag.domain.Tag;
import io.github.juniqlim.realworld.tag.repository.TagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TagUseCaseTest {
    TagUseCase tagUseCase = new TagUseCase(new TagRepository.TagArrayListRepository());

    @BeforeEach
    void setUp() {
        tagUseCase.merge(new TagUseCase.Request(Fixture.LONG_ID_ONE, Arrays.asList("java")));
        tagUseCase.merge(new TagUseCase.Request(Fixture.LONG_ID_THREE, Arrays.asList("kotlin")));
    }

    @Test
    void findAll() {
        assertEquals(
            Arrays.asList(
                new Tag("java", Collections.singletonList(Fixture.LONG_ID_ONE)),
                new Tag("kotlin", Collections.singletonList(Fixture.LONG_ID_THREE))
            ),
            tagUseCase.findAll()
        );
    }

    @Test
    void findOne() {
        assertEquals(
            Collections.singletonList(Fixture.LONG_ID_ONE),
            tagUseCase.findArticleIdsByTag("java")
        );
    }
}