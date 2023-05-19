package io.github.juniqlim.realworld.comment.repository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.juniqlim.realworld.Id.LongId;
import io.github.juniqlim.realworld.comment.domain.Comment;
import io.github.juniqlim.realworld.user.domain.User.Id;
import org.junit.jupiter.api.Test;

class CommentRepositoryTest {
    @Test
    void save() {
        assertDoesNotThrow(() -> new CommentRepository().save(
            new Comment(new LongId(1), new LongId(1), "This is a comment", new Id(1))
        ));
    }

    @Test
    void find() {
        CommentRepository commentRepository = new CommentRepository();
        commentRepository.save(
            new Comment(new LongId(1), new LongId(1), "This is a comment", new Id(1))
        );
        assertEquals("This is a comment", commentRepository.findByArticleId(new LongId(1)).get(0).body());
    }
}
