package io.github.juniqlim.realworld.article.web;

import io.github.juniqlim.realworld.article.AddComment;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.web.Token;
import java.security.PublicKey;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CreateCommentController {
    private final AddComment addComment;
    private final FindUser findUser;
    private final PublicKey publicKey;

    CreateCommentController(AddComment addComment, FindUser findUser, PublicKey publicKey) {
        this.addComment = addComment;
        this.findUser = findUser;
        this.publicKey = publicKey;
    }

    @PostMapping("/api/articles/{slug}/comments")
    public Response articles(@RequestHeader("Authorization") String token, @PathVariable("slug") String slug, @RequestBody Request request) {
        User loginUser = findUser.find(new Token.Jws(publicKey, token).value());
        return new Response(new CommentResponse(
            addComment.add(new AddComment.Request(slug, request.comment.body, loginUser.id())),
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
