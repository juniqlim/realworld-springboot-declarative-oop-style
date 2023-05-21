package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.article.TagUseCase.Request;
import io.github.juniqlim.realworld.article.domain.Tag;
import io.github.juniqlim.realworld.article.repository.TagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TagUseCaseTest {
    TagUseCase tagUseCase = new TagUseCase(new TagRepository());

    @BeforeEach
    void setUp() {
        List<Request> requests = Arrays.asList(new Request(Fixture.LONG_ID_ONE, "java"),
            new Request(Fixture.LONG_ID_THREE, "kotlin"));
        tagUseCase.merges(requests);
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

    @Test
    void delete() {
        tagUseCase.deleteByTagString("java");

        assertEquals(
            Collections.singletonList(
                new Tag("kotlin", Collections.singletonList(Fixture.LONG_ID_THREE))
            ),
            tagUseCase.findAll()
        );
    }
}