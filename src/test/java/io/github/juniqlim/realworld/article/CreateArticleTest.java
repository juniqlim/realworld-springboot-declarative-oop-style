package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.article.CreateArticle.Request;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleArrayListRepository;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.repository.UserRepository.Collection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateArticleTest {
    @Test
    void test() {
        Collection userRepository = new Collection();
        userRepository.save(Fixture.JAKE);

        Article article = new CreateArticle(new ArticleArrayListRepository(), new FindUser(userRepository))
            .create(
                new Request("How to train your dragon", "Ever wonder how?", "You have to believe",
                    Fixture.JAKE.token())
            );
        assertEquals("how-to-train-your-dragon", article.slug());
    }
}
