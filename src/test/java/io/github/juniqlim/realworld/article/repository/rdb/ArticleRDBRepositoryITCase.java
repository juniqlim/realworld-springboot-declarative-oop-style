package io.github.juniqlim.realworld.article.repository.rdb;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.article.domain.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ArticleRDBRepositoryITCase {
    @Autowired ArticleRDBRepository articleRDBRepository;

    @Test
    void create() {
        articleRDBRepository.save(Fixture.JAKE_ARTICLE);
        Article savedArticle = articleRDBRepository.findBySlug(Fixture.JAKE_ARTICLE.slug());

        assertNotNull(savedArticle.id());
        assertEquals(Fixture.JAKE_ARTICLE.slug(), savedArticle.slug());
    }
}