package io.github.juniqlim.realworld.article;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.juniqlim.realworld.article.UpdateArticle.Request.Builder;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.domain.Tag;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.user.domain.User;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UpdateArticleTest {
    ArticleRepository articleRepository = new ArticleRepository();

    @BeforeEach
    void setUp() {
        articleRepository.save(new Article("How to train your dragon", "Ever wonder how?", "You have to believe",
                new User.Id(1), Arrays.asList(new Tag("reactjs"), new Tag("angularjs"), new Tag("dragons"))));
    }

    @Test
    void test() {
        Article update = new UpdateArticle(articleRepository).update(
            new Builder().userId(new User.Id(1)).slug("how-to-train-your-dragon").title("title-dragon").description("description").body("body")
                .build());

        assertEquals("title-dragon", update.title());
        assertEquals("description", update.description());
        assertEquals("body", update.body());
    }
}
