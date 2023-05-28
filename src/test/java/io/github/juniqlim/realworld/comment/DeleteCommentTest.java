package io.github.juniqlim.realworld.comment;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.Id.LongId;
import io.github.juniqlim.realworld.comment.DeleteComment.Request;
import io.github.juniqlim.realworld.comment.domain.Comment;
import io.github.juniqlim.realworld.comment.repository.CommentRepository;
import io.github.juniqlim.realworld.user.FindUser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteCommentTest {
    @Test
    void test() {
        CommentRepository commentRepository = Fixture.COMMENT_REPOSITORY;
        FindUser findUser = new FindUser(Fixture.USER_REPOSITORY);

        new DeleteComment(commentRepository, findUser)
            .delete(new Request(new LongId(3), Fixture.JAKE.id()));
        new DeleteComment(commentRepository, findUser)
            .delete(new Request(new LongId(4), Fixture.JUNIQ.id()));

        List<Comment> comments = new FindComment(commentRepository)
            .comments(Fixture.MINK_ARTICLE.id());

        assertEquals(0, comments.size());
    }
}
