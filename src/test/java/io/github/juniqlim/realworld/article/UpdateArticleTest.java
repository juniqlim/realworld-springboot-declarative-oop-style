package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.article.UpdateArticle.Request.Builder;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleArrayListRepository;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateArticleTest {
    ArticleRepository articleRepository = new ArticleArrayListRepository();

    @BeforeEach
    void setUp() {
        articleRepository.save(new Article(Fixture.LONG_ID_ONE, "How to train your dragon", "Ever wonder how?", "You have to believe",
                Fixture.LONG_ID_ONE));
    }

    @Test
    void test() {
        Article update = new UpdateArticle(articleRepository).update(
            new Builder().userId(Fixture.LONG_ID_ONE).slug("how-to-train-your-dragon").title("title-dragon").description("description").body("body")
                .build());

        assertEquals("title-dragon", update.title());
        assertEquals("description", update.description());
        assertEquals("body", update.body());
    }
}
