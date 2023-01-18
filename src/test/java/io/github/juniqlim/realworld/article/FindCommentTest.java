package io.github.juniqlim.realworld.article;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.juniqlim.realworld.TestPublicKey;
import io.github.juniqlim.realworld.TestRepository;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.article.web.CommentResponse;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.FollowUser;
import io.github.juniqlim.realworld.user.User.UserByToken;
import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import io.github.juniqlim.realworld.user.repository.UserRepository.Collection;
import io.github.juniqlim.realworld.user.web.Token2.Jws;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import org.junit.jupiter.api.Test;

class FindCommentTest {
    @Test
    void test() throws InvalidKeySpecException, NoSuchAlgorithmException {
        TestRepository testRepository = new TestRepository(new Collection(),
            new ArticleRepository());
        ArticleRepository articleRepository = testRepository.articleRepository();
        UserRepository userRepository = testRepository.userRepository();

        User mink = userRepository.findByUsername("mink");
        new FollowUser(userRepository).follow(mink.token(), "juniq");

        io.github.juniqlim.realworld.user.User loginUser = new UserByToken(new FindUser(userRepository),
            new Jws(new TestPublicKey().publicKey(), "token " + mink.token()));
        List<CommentResponse> comments = new FindComment(articleRepository, new FindUser(userRepository)).comments(
            "learn-elm", loginUser);

        assertEquals(2, comments.size());
        assertFalse(comments.get(0).getAuthor().isFollowing());
        assertTrue(comments.get(1).getAuthor().isFollowing());
    }
}
