package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.article.CreateArticle.Request;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleArrayListRepository;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateArticleTest {
    CreateArticle createArticle;

    @BeforeEach
    void setUp() {
        UserRepository userRepository = new UserRepository.UserArrayListRepository();
        userRepository.save(Fixture.JAKE);

        createArticle = new CreateArticle(new ArticleArrayListRepository(), new FindUser(userRepository));
    }

    @Test
    void test() {
        Article article = createArticle.create(
            new Request(
                "How to train your dragon",
                "Ever wonder how?",
                "You have to believe",
                Fixture.JAKE.id())
        );
        assertEquals("how-to-train-your-dragon", article.slug());
    }
}
