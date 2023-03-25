package io.github.juniqlim.realworld.comment.web;

import io.github.juniqlim.realworld.comment.DeleteComment;
import io.github.juniqlim.realworld.user.web.Token;
import java.security.PublicKey;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
class DeleteCommentController {
    private final DeleteComment deleteComment;
    private final PublicKey publicKey;

    DeleteCommentController(DeleteComment deleteComment, PublicKey publicKey) {
        this.deleteComment = deleteComment;
        this.publicKey = publicKey;
    }

    @DeleteMapping("/api/articles/{slug}/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@RequestHeader("Authorization") String token, @PathVariable("slug") String slug, @PathVariable("id") Long id) {
        deleteComment.delete(new DeleteComment.Request(slug, id, new Token.Jws(publicKey, token).value()));
    }
}
