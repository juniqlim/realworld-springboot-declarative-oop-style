package io.github.juniqlim.realworld.comment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.comment.AddComment.Request;
import io.github.juniqlim.realworld.comment.domain.Comment;
import io.github.juniqlim.realworld.comment.repository.CommentRepository;
import org.junit.jupiter.api.Test;

class AddCommentTest {
    @Test
    void test() {
        Comment comment = new AddComment(new CommentRepository()).add(new Request(Fixture.JAKE_ARTICLE.id(), "This is a comment", Fixture.LONG_ID_ONE));
        assertEquals("This is a comment", comment.body());
    }
}
