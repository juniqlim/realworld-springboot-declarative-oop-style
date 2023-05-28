package io.github.juniqlim.realworld.comment.web;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.article.FindArticle;
import io.github.juniqlim.realworld.auth.HeaderAuthStringTo;
import io.github.juniqlim.realworld.comment.AddComment;
import io.github.juniqlim.realworld.user.FindProfile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CreateCommentController {
    private final AddComment addComment;
    private final FindArticle findArticle;
    private final FindProfile findProfile;

    public CreateCommentController(AddComment addComment, FindArticle findArticle, FindProfile findProfile) {
        this.addComment = addComment;
        this.findArticle = findArticle;
        this.findProfile = findProfile;
    }

    @PostMapping("/api/articles/{slug}/comments")
    public Response articles(@RequestHeader("Authorization") String headerAuthString, @PathVariable("slug") String slug, @RequestBody Request request) {
        Id loginUserId = HeaderAuthStringTo.userId(headerAuthString);
        return new Response(new CommentResponse(
            addComment.add(new AddComment.Request(
                findArticle.find(slug).id(),
                request.comment.body,
                loginUserId)),
            findProfile.find(loginUserId)
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
