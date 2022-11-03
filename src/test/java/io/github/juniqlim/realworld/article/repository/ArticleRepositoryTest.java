package io.github.juniqlim.realworld.article.repository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import io.github.juniqlim.realworld.article.domain.Article;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class ArticleRepositoryTest {
    @Test
    void save() {
        ArticleRepository articleRepository = new ArticleRepository();
        Article article = new Article("How to train your dragon", "Ever wonder how?", "You have to believe",
            Arrays.asList("reactjs", "angularjs", "dragons"));

        assertDoesNotThrow(() -> articleRepository.save(article));
    }
}