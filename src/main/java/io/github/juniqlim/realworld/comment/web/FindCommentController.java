package io.github.juniqlim.realworld.comment.web;

import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.comment.FindCommentResponse;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.web.Token;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.security.PublicKey;
import java.util.List;

@RestController
class FindCommentController {
    private final FindCommentResponse findCommentResponse;
    private final FindUser findUser;
    private final PublicKey publicKey;
    private final ArticleRepository articleRepository;

    public FindCommentController(FindCommentResponse findCommentResponse, FindUser findUser, PublicKey publicKey, ArticleRepository articleRepository) {
        this.findCommentResponse = findCommentResponse;
        this.findUser = findUser;
        this.publicKey = publicKey;
        this.articleRepository = articleRepository;
    }

    @GetMapping("/api/articles/{slug}/comments")
    public Response articles(@RequestHeader(name = "Authorization", required = false) String token, @PathVariable("slug") String slug) {
        return new Response(
            findCommentResponse.comments(
                articleRepository.findBySlug(slug).id(),
                findUser.find(new Token.Jws(publicKey, token)).id()
            )
        );
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
