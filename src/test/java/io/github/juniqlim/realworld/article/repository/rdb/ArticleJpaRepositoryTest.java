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
        articleJpaRepository.save(entity);
        ArticleEntity articleEntity = articleJpaRepository.findBySlug(Fixture.JAKE_ARTICLE.slug());
        assertEquals(articleEntity.getId(), entity.getId());
    }
}