package io.github.juniqlim.realworld.comment;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.comment.domain.Comment;
import io.github.juniqlim.realworld.comment.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class AddComment {
    private final CommentRepository commentRepository;

    public AddComment(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment add(Request request) {
        Comment comment = new Comment(commentRepository.createCommentId(), request.articleId,
            request.comment, request.userId);
        commentRepository.save(comment);
        return comment;
    }

    public static class Request {
        private final Id articleId;
        private final String comment;
        private final Id userId;

        public Request(Id articleId, String comment, Id userId) {
            this.articleId = articleId;
            this.comment = comment;
            this.userId = userId;
        }
    }
}
