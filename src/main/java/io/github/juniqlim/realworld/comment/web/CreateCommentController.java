package io.github.juniqlim.realworld.comment.web;

import io.github.juniqlim.realworld.Id.LongId;
import io.github.juniqlim.realworld.auth.HeaderAuthStringTo;
import io.github.juniqlim.realworld.comment.AddComment;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.domain.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CreateCommentController {
    private final AddComment addComment;
    private final FindUser findUser;

    CreateCommentController(AddComment addComment, FindUser findUser) {
        this.addComment = addComment;
        this.findUser = findUser;
    }

    @PostMapping("/api/articles/{slug}/comments")
    public Response articles(@RequestHeader("Authorization") String headerAuthString, @PathVariable("slug") String slug, @RequestBody Request request) {
        User loginUser = findUser.find(HeaderAuthStringTo.userId(headerAuthString));
        return new Response(new CommentResponse(
            addComment.add(new AddComment.Request(new LongId(1), request.comment.body, loginUser.id())),
            loginUser.profile()
        ));
    }

    private static class Request {
        private Comment comment;

        public Comment getComment() {
            return comment;
        }

        private static class Comment {
            private String body;

            public String getBody() {
                return body;
            }
        }
    }

    private static class Response {
        private CommentResponse comment;

        Response(CommentResponse comment) {
            this.comment = comment;
        }

        public CommentResponse getComment() {
            return comment;
        }
    }
}
