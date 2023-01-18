package io.github.juniqlim.realworld.article.web;

import io.github.juniqlim.realworld.article.FindComment;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.User;
import io.github.juniqlim.realworld.user.web.Token;
import java.security.PublicKey;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
class FindCommentController {
    private final FindComment findComment;
    private final FindUser findUser;
    private final PublicKey publicKey;

    FindCommentController(FindComment findComment, FindUser findUser, PublicKey publicKey) {
        this.findComment = findComment;
        this.findUser = findUser;
        this.publicKey = publicKey;
    }

    @GetMapping("/api/articles/{slug}/comments")
    public Response articles(@RequestHeader(name = "Authorization", required = false) String token, @PathVariable("slug") String slug) {
        return new Response(findComment.comments(slug, new User.UserByToken(findUser, new Token.Jws(publicKey, token))));
    }

    private static class Response {
        private List<CommentResponse> comments;

        Response(List<CommentResponse> comments) {
            this.comments = comments;
        }

        public List<CommentResponse> getComments() {
            return comments;
        }
    }
}
