package io.github.juniqlim.realworld.article;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import autoparams.AutoSource;
import io.github.juniqlim.realworld.TestRepository;
import io.github.juniqlim.realworld.article.FindComment.ResultComment;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.user.FindProfile;
import io.github.juniqlim.realworld.user.FollowUser;
import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import io.github.juniqlim.realworld.user.repository.UserRepository.Collection;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;

class FindCommentTest {
    @ParameterizedTest
    @AutoSource
    void test() throws InvalidKeySpecException, NoSuchAlgorithmException {
        TestRepository testRepository = new TestRepository(new Collection(),
            new ArticleRepository());
        ArticleRepository articleRepository = testRepository.articleRepository();
        UserRepository userRepository = testRepository.userRepository();
        User mink = userRepository.findByUsername("mink");
        new FollowUser(userRepository).follow(mink.token(), "juniq");

        List<ResultComment> comments = new FindComment(articleRepository, new FindProfile(userRepository)).comments(
            "learn-elm", mink.token());

        assertEquals(2, comments.size());
        assertFalse(comments.get(0).getAuthor().isFollowing());
        assertTrue(comments.get(1).getAuthor().isFollowing());
    }
}
