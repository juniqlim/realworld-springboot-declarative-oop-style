package io.github.juniqlim.realworld.comment;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.comment.web.CommentResponse;
import io.github.juniqlim.realworld.user.FindProfile;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.FollowUser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FindCommentResponseTest {
    @Test
    void test() {
        new FollowUser(Fixture.USER_REPOSITORY).follow(Fixture.MINK.id(), Fixture.JUNIQ.id());

        List<CommentResponse> comments = new FindCommentResponse(
            new FindComment(Fixture.COMMENT_REPOSITORY),
            new FindProfile(
                new FindUser(Fixture.USER_REPOSITORY),
                new FollowUser(Fixture.USER_REPOSITORY)
            )
        ).comments(Fixture.MINK_ARTICLE.id(), Fixture.MINK.id());

        assertEquals(2, comments.size());
        assertFalse(comments.get(0).getAuthor().isFollowing());
        assertTrue(comments.get(1).getAuthor().isFollowing());
    }
}