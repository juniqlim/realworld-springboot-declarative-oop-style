package io.github.juniqlim.realworld.comment;

import static org.junit.jupiter.api.Assertions.*;

import io.github.juniqlim.realworld.TestRepository;
import io.github.juniqlim.realworld.comment.DeleteComment.Request;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import io.github.juniqlim.realworld.user.repository.UserRepository.Collection;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import org.junit.jupiter.api.Test;

class DeleteCommentTest {
    @Test
    void test() throws InvalidKeySpecException, NoSuchAlgorithmException {
        TestRepository testRepository = new TestRepository(new Collection(),
            new ArticleRepository());
        ArticleRepository articleRepository = testRepository.articleRepository();
        UserRepository userRepository = testRepository.userRepository();
        User juniq = userRepository.findByUsername("juniq");

        new DeleteComment(articleRepository, new FindUser(userRepository)).delete(new Request("learn-elm", 3, juniq.token()));
        new DeleteComment(articleRepository, new FindUser(userRepository)).delete(new Request("learn-elm", 4, juniq.token()));

        assertEquals(1, articleRepository.findBySlug("learn-elm").comments().size());
    }
}
