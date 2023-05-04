package io.github.juniqlim.realworld.comment.repository;

import io.github.juniqlim.realworld.article.domain.Comment;
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

    List<Comment> findByArticleId(long articleId) {
        return comments.stream()
            .filter(comment -> comment.articleId() == articleId)
            .collect(Collectors.toList());
    }

    public long findCommentSequence() {
        return commentSequence.getAndIncrement();
    }
}
