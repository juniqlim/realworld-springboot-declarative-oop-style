package io.github.juniqlim.realworld.comment;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.comment.repository.CommentRepository;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class DeleteComment {
    private final CommentRepository commentRepository;
    private final FindUser findUser;

    DeleteComment(CommentRepository commentRepository, FindUser findUser) {
        this.commentRepository = commentRepository;
        this.findUser = findUser;
    }

    public void delete(DeleteComment.Request request) {
        User user = findUser.find(request.loginUserId);
        commentRepository.deleteComment(request.commentId, user.id());
    }

    public static class Request {
        private final Id commentId;
        private final Id loginUserId;

        public Request(Id commentId, Id loginUserId) {
            this.commentId = commentId;
            this.loginUserId = loginUserId;
        }
    }
}
