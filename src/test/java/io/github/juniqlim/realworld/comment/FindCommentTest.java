package io.github.juniqlim.realworld.comment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.TestPublicKey;
import io.github.juniqlim.realworld.comment.web.CommentResponse;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.FollowUser;
import io.github.juniqlim.realworld.user.User.UserByToken;
import io.github.juniqlim.realworld.user.web.Token;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import org.junit.jupiter.api.Test;

class FindCommentTest {
    @Test
    void test() throws InvalidKeySpecException, NoSuchAlgorithmException {
        new FollowUser(Fixture.USER_REPOSITORY).follow(Fixture.MINK.token(), "juniq");

        io.github.juniqlim.realworld.user.User loginUser = new UserByToken(new FindUser(Fixture.USER_REPOSITORY),
            new Token.Jws(new TestPublicKey().publicKey(), "token " + Fixture.MINK.token()));
        List<CommentResponse> comments = new FindComment(Fixture.ARTICLE_REPOSITORY, new FindUser(Fixture.USER_REPOSITORY)).comments(
            "learn-elm", loginUser);

        assertEquals(2, comments.size());
        assertFalse(comments.get(0).getAuthor().isFollowing());
        assertTrue(comments.get(1).getAuthor().isFollowing());
    }
}