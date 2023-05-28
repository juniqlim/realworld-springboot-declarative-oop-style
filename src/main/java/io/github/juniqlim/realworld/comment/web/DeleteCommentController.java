package io.github.juniqlim.realworld.comment.web;

import io.github.juniqlim.realworld.Id.LongId;
import io.github.juniqlim.realworld.auth.HeaderAuthStringTo;
import io.github.juniqlim.realworld.comment.DeleteComment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
class DeleteCommentController {
    private final DeleteComment deleteComment;

    DeleteCommentController(DeleteComment deleteComment) {
        this.deleteComment = deleteComment;
    }

    @DeleteMapping("/api/articles/{slug}/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@RequestHeader("Authorization") String headerAuthString, @PathVariable("slug") String slug, @PathVariable("id") Long id) {
        deleteComment.delete(new DeleteComment.Request(new LongId(id), HeaderAuthStringTo.userId(headerAuthString)));
    }
}
