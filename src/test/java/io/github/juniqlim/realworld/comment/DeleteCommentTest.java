package io.github.juniqlim.realworld.comment;

import static org.junit.jupiter.api.Assertions.*;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.comment.DeleteComment.Request;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import org.junit.jupiter.api.Test;

class DeleteCommentTest {
    @Test
    void test() {
        UserRepository userRepository = new UserRepository.Collection();
        userRepository.save(Fixture.JUNIQ);

        ArticleRepository articleRepository = new ArticleRepository();
        articleRepository.save(Fixture.MINK_ARTICLE);

        new DeleteComment(articleRepository, new FindUser(userRepository)).delete(new Request("learn-elm", 3, Fixture.JUNIQ.token()));
        new DeleteComment(articleRepository, new FindUser(userRepository)).delete(new Request("learn-elm", 4, Fixture.JUNIQ.token()));

        assertEquals(1, articleRepository.findBySlug("learn-elm").comments().size());
    }
}
