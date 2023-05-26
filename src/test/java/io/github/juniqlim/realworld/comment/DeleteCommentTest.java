package io.github.juniqlim.realworld.comment;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.Id.LongId;
import io.github.juniqlim.realworld.comment.DeleteComment.Request;
import io.github.juniqlim.realworld.comment.repository.CommentRepository;
import io.github.juniqlim.realworld.comment.web.CommentResponse;
import io.github.juniqlim.realworld.user.FindProfile;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.FollowUser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteCommentTest {
    @Test
    void test() {
        CommentRepository commentRepository = Fixture.COMMENT_REPOSITORY;
        FindUser findUser = new FindUser(Fixture.USER_REPOSITORY);

        new DeleteComment(commentRepository, findUser)
            .delete(new Request(new LongId(3), Fixture.JAKE.token()));
        new DeleteComment(commentRepository, findUser)
            .delete(new Request(new LongId(4), Fixture.JUNIQ.token()));

        List<CommentResponse> comments = new FindComment(commentRepository, new FindProfile(findUser, new FollowUser(Fixture.USER_REPOSITORY)))
            .comments(Fixture.MINK_ARTICLE.id(), Fixture.JUNIQ.id());

        assertEquals(0, comments.size());
    }
}
