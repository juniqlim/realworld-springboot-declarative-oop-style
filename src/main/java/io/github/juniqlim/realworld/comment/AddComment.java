package io.github.juniqlim.realworld.comment;

import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.domain.Comment;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.comment.repository.CommentRepository;
import io.github.juniqlim.realworld.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class AddComment {
    private final CommentRepository commentRepository;

    public AddComment(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment add(Request request) {
        Comment comment = new Comment(commentRepository.findCommentSequence(), request.articleId,
            request.comment, request.userId);
        commentRepository.save(comment);
        return comment;
    }

    public static class Request {
        private final long articleId;
        private final String comment;
        private final User.Id userId;

        public Request(long articleId, String comment, User.Id userId) {
            this.articleId = articleId;
            this.comment = comment;
            this.userId = userId;
        }
    }
}
