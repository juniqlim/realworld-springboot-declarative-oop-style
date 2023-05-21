package io.github.juniqlim.realworld.article.repository.rdb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled
class ArticleJpaRepositoryTest {
    @Autowired ArticleJpaRepository articleJpaRepository;

    @Test
    void create() {
        articleJpaRepository.save(RDBFixture.JAKE_ARTICLE_ENTITY);
        articleJpaRepository.save(RDBFixture.JUNIQ_ARTICLE_ENTITY);

        assertEquals(RDBFixture.JAKE_ARTICLE_ENTITY.getId(), articleJpaRepository.findBySlug(RDBFixture.JAKE_ARTICLE_ENTITY.getSlug()).getId());
        assertEquals(RDBFixture.JUNIQ_ARTICLE_ENTITY.getId(), articleJpaRepository.findBySlug(RDBFixture.JUNIQ_ARTICLE_ENTITY.getSlug()).getId());
        assertEquals("1,3", articleJpaRepository.findBySlug(RDBFixture.JAKE_ARTICLE_ENTITY.getSlug()).getFavoriteUserIds());
        assertEquals(RDBFixture.JAKE_ARTICLE_ENTITY.getFavoriteUserIds(), articleJpaRepository.findBySlug(RDBFixture.JAKE_ARTICLE_ENTITY.getSlug()).getFavoriteUserIds());
        assertEquals(RDBFixture.JUNIQ_ARTICLE_ENTITY.getFavoriteUserIds(), articleJpaRepository.findBySlug(RDBFixture.JUNIQ_ARTICLE_ENTITY.getSlug()).getFavoriteUserIds());
    }

    @Test
    void sequence() {
        assertEquals(1, articleJpaRepository.sequence());
        assertEquals(2, articleJpaRepository.sequence());
        assertEquals(3, articleJpaRepository.sequence());
        assertEquals(4, articleJpaRepository.sequence());
        assertEquals(5, articleJpaRepository.sequence());
    }
}