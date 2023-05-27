package io.github.juniqlim.realworld.article.repository.rdb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class ArticleJpaRepositoryIT {
    @Autowired ArticleJpaRepository articleJpaRepository;

    @Test
    void create() {
        articleJpaRepository.save(RDBFixture.JAKE_ARTICLE_ENTITY);
        articleJpaRepository.save(RDBFixture.JUNIQ_ARTICLE_ENTITY);

        assertEquals(RDBFixture.JAKE_ARTICLE_ENTITY.getSlug(), articleJpaRepository.findBySlug(RDBFixture.JAKE_ARTICLE_ENTITY.getSlug()).getSlug());
        assertEquals(RDBFixture.JUNIQ_ARTICLE_ENTITY.getSlug(), articleJpaRepository.findBySlug(RDBFixture.JUNIQ_ARTICLE_ENTITY.getSlug()).getSlug());
        assertEquals(1, articleJpaRepository.findBySlug(RDBFixture.JAKE_ARTICLE_ENTITY.getSlug()).getFavoritesCount());
        assertEquals(RDBFixture.JAKE_ARTICLE_ENTITY.getFavoritesCount(), articleJpaRepository.findBySlug(RDBFixture.JAKE_ARTICLE_ENTITY.getSlug()).getFavoritesCount());
        assertEquals(RDBFixture.JUNIQ_ARTICLE_ENTITY.getFavoritesCount(), articleJpaRepository.findBySlug(RDBFixture.JUNIQ_ARTICLE_ENTITY.getSlug()).getFavoritesCount());
    }

    @Test
    void sequence() {
        long sequence = articleJpaRepository.sequence();
        assertEquals(sequence + 1, articleJpaRepository.sequence());
        assertEquals(sequence + 2, articleJpaRepository.sequence());
        assertEquals(sequence + 3, articleJpaRepository.sequence());
        assertEquals(sequence + 4, articleJpaRepository.sequence());
    }
}