package io.github.juniqlim.realworld.tag.repository.rdb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@DataJpaTest
class TagJpaRepositoryITCase {
    @Autowired TagJpaRepository tagJpaRepository;

    @Test
    void test() {
        tagJpaRepository.saveAllAndFlush(Collections.singletonList(new TagEntity("tag")));
        tagJpaRepository.saveAll(Arrays.asList(new TagEntity("tag"), new TagEntity("tag1"), new TagEntity("tag2")));

        List<TagEntity> all = tagJpaRepository.findAll();
        all.forEach(x -> System.out.println(x.id() + " " + x.tag()));
    }
}