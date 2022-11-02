package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.article.CreateArticle.Request;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import org.junit.jupiter.api.Test;

class CreateArticleTest {
    @Test
    void test() {
        UserRepository userRepository = new UserRepository();
        Article article = new CreateArticle().create(new Request());
    }
}
