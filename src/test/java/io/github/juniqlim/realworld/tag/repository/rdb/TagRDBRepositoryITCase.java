package io.github.juniqlim.realworld.tag.repository.rdb;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.tag.domain.Tags;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Arrays;

@DataJpaTest
@Import(TagRDBRepository.class)
class TagRDBRepositoryITCase {
    @Autowired TagRDBRepository tagRDBRepository;

    @Test
    void test() {
        tagRDBRepository.merges(new Tags(Fixture.LONG_ID_ONE, Arrays.asList("tag1", "tag2", "tag3")));
        tagRDBRepository.merges(new Tags(Fixture.LONG_ID_TWO, Arrays.asList("tag1", "tag2", "tag4")));
        tagRDBRepository.findAll().forEach(System.out::println);

        tagRDBRepository.findByArticleId(Fixture.LONG_ID_ONE).forEach(System.out::println);
        tagRDBRepository.findByArticleId(Fixture.LONG_ID_TWO).forEach(System.out::println);
    }
}