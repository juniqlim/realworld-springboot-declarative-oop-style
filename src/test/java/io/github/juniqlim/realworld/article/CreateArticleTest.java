package io.github.juniqlim.realworld.article;

import static org.junit.jupiter.api.Assertions.assertEquals;

import autoparams.AutoSource;
import io.github.juniqlim.realworld.article.CreateArticle.Request;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.article.repository.TagRepository;
import io.github.juniqlim.realworld.user.FindProfile;
import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.repository.UserRepository.Collection;
import java.util.Arrays;
import org.junit.jupiter.params.ParameterizedTest;

class CreateArticleTest {
    @ParameterizedTest
    @AutoSource
    void test(User user) {
        Collection userRepository = new Collection();
        userRepository.save(user);

        Article article = new CreateArticle(new ArticleRepository(), new FindProfile(userRepository), new TagUseCase(new TagRepository())).create(
                new Request("How to train your dragon", "Ever wonder how?", "You have to believe",
                    user.token(), Arrays.asList("reactjs", "angularjs", "dragons")));
        assertEquals("how-to-train-your-dragon", article.slug());
    }
}
