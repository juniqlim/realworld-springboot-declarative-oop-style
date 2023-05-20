package io.github.juniqlim.realworld.article.repository.rdb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.juniqlim.realworld.Fixture;
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
        ArticleEntity entity = new ArticleToArticleEntity().articleEntity(Fixture.JAKE_ARTICLE);
        ArticleEntity entity2 = new ArticleToArticleEntity().articleEntity(Fixture.JUNIQ_ARTICLE);
        articleJpaRepository.save(entity);
        articleJpaRepository.save(entity2);
        ArticleEntity articleEntity = articleJpaRepository.findBySlug(Fixture.JAKE_ARTICLE.slug());
        assertEquals(entity.getId(), articleEntity.getId());
        assertEquals(1, articleEntity.getId());
        assertEquals(2, articleJpaRepository.findBySlug(Fixture.JUNIQ_ARTICLE.slug()).getId());
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