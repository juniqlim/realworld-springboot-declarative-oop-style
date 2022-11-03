package io.github.juniqlim.realworld.article;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.juniqlim.realworld.article.UpdateArticle.Request.Builder;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UpdateArticleTest {
    ArticleRepository articleRepository = new ArticleRepository();

    @BeforeEach
    void setUp() {
        articleRepository.save(new Article("How to train your dragon", "Ever wonder how?", "You have to believe",
            Arrays.asList("reactjs", "angularjs", "dragons")));
    }

    @Test
    void test() {
        Article update = new UpdateArticle(articleRepository).update(
            new Builder().slug("how-to-train-your-dragon").title("title-dragon").description("description").body("body")
                .build());

        assertEquals("title-dragon", update.getTitle());
        assertEquals("description", update.getDescription());
        assertEquals("body", update.getBody());
    }
}
