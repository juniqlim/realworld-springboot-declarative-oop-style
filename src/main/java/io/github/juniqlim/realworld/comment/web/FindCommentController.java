package io.github.juniqlim.realworld.comment.web;

import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.auth.HeaderAuthStringTo;
import io.github.juniqlim.realworld.comment.FindCommentResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class FindCommentController {
    private final FindCommentResponse findCommentResponse;
    private final ArticleRepository articleRepository;

    public FindCommentController(FindCommentResponse findCommentResponse, ArticleRepository articleRepository) {
        this.findCommentResponse = findCommentResponse;
        this.articleRepository = articleRepository;
    }

    @GetMapping("/api/articles/{slug}/comments")
    public Response articles(@RequestHeader(name = "Authorization", required = false) String headerAuthString, @PathVariable("slug") String slug) {
        return new Response(
            findCommentResponse.comments(
                articleRepository.findBySlug(slug).id(),
                HeaderAuthStringTo.userId(headerAuthString)
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
