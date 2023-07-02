package io.github.juniqlim.realworld.comment;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.comment.AddComment.Request;
import io.github.juniqlim.realworld.comment.domain.Comment;
import io.github.juniqlim.realworld.comment.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddCommentTest {
    AddComment addComment;

    @BeforeEach
    void setUp() {
        addComment = new AddComment(new CommentRepository.ListRepository());
    }

    @Test
    void test() {
        Comment comment = addComment.add(new Request(Fixture.JAKE_ARTICLE.id(), "This is a comment", Fixture.LONG_ID_ONE));
        assertEquals("This is a comment", comment.body());
    }
}
