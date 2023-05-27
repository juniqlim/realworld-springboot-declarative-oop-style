package io.github.juniqlim.realworld.comment.repository.rdb;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.comment.domain.Comment;
import io.github.juniqlim.realworld.comment.repository.CommentRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Repository
class CommentRDBRepository implements CommentRepository {
    private final CommentJpaRepository commentJpaRepository;
    private final CommentToCommentEntity commentToCommentEntity = new CommentToCommentEntity();

    public CommentRDBRepository(CommentJpaRepository commentJpaRepository) {
        this.commentJpaRepository = commentJpaRepository;
    }

    @Override
    public void save(Comment comment) {
        commentJpaRepository.save(commentToCommentEntity.commentEntity(comment));
    }

    @Override
    public List<Comment> findByArticleId(Id articleId) {
        return commentJpaRepository.findByArticleId(articleId.value()).stream()
            .map(commentToCommentEntity::comment)
            .collect(Collectors.toList());
    }

    @Override
    public Id createCommentId() {
        return new Id.LongId(commentJpaRepository.sequence());
    }

    @Override
    public void deleteComment(Id commentId, Id loginUserId) {
        commentJpaRepository.deleteByIdAndCommenterUserId(commentId.value(), loginUserId.value());
    }
}
