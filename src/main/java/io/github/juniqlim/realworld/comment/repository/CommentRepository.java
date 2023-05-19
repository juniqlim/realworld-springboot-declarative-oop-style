package io.github.juniqlim.realworld.comment.repository;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.Id.LongId;
import io.github.juniqlim.realworld.comment.domain.Comment;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepository {
    private final List<Comment> comments = new ArrayList<>();
    private final AtomicLong commentSequence = new AtomicLong(1);

    public void save(Comment comment) {
        comments.add(comment);
    }

    List<Comment> findByArticleId(Id articleId) {
        return comments.stream()
            .filter(comment -> comment.articleId().equals(articleId))
            .collect(Collectors.toList());
    }

    public long findCommentSequence() {
        return commentSequence.getAndIncrement();
    }

    public Id createCommentId() {
        return new LongId(commentSequence.getAndIncrement());
    }
}
