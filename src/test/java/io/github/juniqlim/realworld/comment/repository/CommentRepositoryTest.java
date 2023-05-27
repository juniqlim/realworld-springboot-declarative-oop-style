package io.github.juniqlim.realworld.comment.repository;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.Id.LongId;
import io.github.juniqlim.realworld.comment.domain.Comment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CommentRepositoryTest {
    @Test
    void save() {
        assertDoesNotThrow(() -> new CommentRepository.ListRepository().save(
            new Comment(new LongId(1), new LongId(1), "This is a comment", Fixture.LONG_ID_ONE)
        ));
    }

    @Test
    void find() {
        CommentRepository commentRepository = new CommentRepository.ListRepository();
        commentRepository.save(
            new Comment(new LongId(1), new LongId(1), "This is a comment", Fixture.LONG_ID_ONE)
        );
        assertEquals("This is a comment", commentRepository.findByArticleId(new LongId(1)).get(0).body());
    }
}
