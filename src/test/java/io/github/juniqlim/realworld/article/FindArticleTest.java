package io.github.juniqlim.realworld.article;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FindArticleTest {
    ArticleRepository articleRepository = new ArticleRepository();

    @BeforeEach
    void setUp() {
        articleRepository.save(new Article("How to train your dragon", "Ever wonder how?", "You have to believe",
            "idid", Arrays.asList("reactjs", "angularjs", "dragons")));
    }

    @Test
    void test() {
        assertEquals("How to train your dragon",
            new FindArticle(articleRepository).find("how-to-train-your-dragon").getTitle());
    }

    @Test
    void findByAuthor() {
        assertEquals("How to train your dragon",
            new FindArticle(articleRepository).find(new FindArticle.Request(null, "idid", null, 0, 100))
                .get(0).getTitle());
    }
}
