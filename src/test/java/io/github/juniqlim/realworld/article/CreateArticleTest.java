package io.github.juniqlim.realworld.article;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.juniqlim.realworld.article.CreateArticle.Request;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class CreateArticleTest {
    @Test
    void test() {
        Article article = new CreateArticle(new ArticleRepository()).create(
            new Request("How to train your dragon", "Ever wonder how?", "You have to believe",
                Arrays.asList("reactjs", "angularjs", "dragons")));
        assertEquals("how-to-train-your-dragon", article.getSlug());
    }
}
