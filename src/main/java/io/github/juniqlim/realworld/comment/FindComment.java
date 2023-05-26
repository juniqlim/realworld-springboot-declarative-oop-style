package io.github.juniqlim.realworld.comment;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.comment.domain.Comment;
import io.github.juniqlim.realworld.comment.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindComment {
    private final CommentRepository commentRepository;

    public FindComment(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> comments(Id articleId) {
        return commentRepository.findByArticleId(articleId);
    }
}
