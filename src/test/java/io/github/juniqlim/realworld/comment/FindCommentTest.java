package io.github.juniqlim.realworld.comment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.comment.web.CommentResponse;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.FollowUser;
import java.util.List;
import org.junit.jupiter.api.Test;

class FindCommentTest {
    @Test
    void test() {
        new FollowUser(Fixture.USER_REPOSITORY).follow(Fixture.MINK.token(), "juniq");

        List<CommentResponse> comments = new FindComment(Fixture.COMMENT_REPOSITORY, new FindUser(Fixture.USER_REPOSITORY)).comments(
            Fixture.MINK_ARTICLE.id(), Fixture.MINK.id());

        assertEquals(2, comments.size());
        assertFalse(comments.get(0).getAuthor().isFollowing());
        assertTrue(comments.get(1).getAuthor().isFollowing());
    }
}