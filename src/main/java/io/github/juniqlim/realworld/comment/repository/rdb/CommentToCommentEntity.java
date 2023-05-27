package io.github.juniqlim.realworld.comment.repository.rdb;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.comment.domain.Comment;

class CommentToCommentEntity {
    CommentEntity commentEntity(Comment comment) {
        return new CommentEntity(comment.id().value(), comment.articleId().value(), comment.body(),
            comment.commenterUserId().value());
    }

    public Comment comment(CommentEntity commentEntity) {
        return new Comment(new Id.LongId(commentEntity.id()), new Id.LongId(commentEntity.articleId()),
            commentEntity.body(), new Id.LongId(commentEntity.commenterUserId()));
    }
}
