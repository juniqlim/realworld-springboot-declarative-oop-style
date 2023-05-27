package io.github.juniqlim.realworld.comment.repository;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.Id.LongId;
import io.github.juniqlim.realworld.comment.domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public interface CommentRepository {
    void save(Comment comment);
    List<Comment> findByArticleId(Id articleId);
    Id createCommentId();
    void deleteComment(Id commentId, Id loginUserId);

    class ListRepository implements CommentRepository {
        private final List<Comment> comments = new ArrayList<>();
        private final AtomicLong commentSequence = new AtomicLong(1);

        public void save(Comment comment) {
            comments.add(comment);
        }

        public List<Comment> findByArticleId(Id articleId) {
            return comments.stream()
                .filter(comment -> comment.articleId().equals(articleId))
                .collect(Collectors.toList());
        }

        public Id createCommentId() {
            return new LongId(commentSequence.getAndIncrement());
        }

        public void deleteComment(Id commentId, Id loginUserId) {
            comments.removeIf(comment -> comment.id().equals(commentId)
                && comment.commenterUserId().equals(loginUserId));
        }
    }
}
