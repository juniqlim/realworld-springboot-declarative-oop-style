package io.github.juniqlim.realworld.article.web;

import io.github.juniqlim.realworld.article.DeleteArticle;
import io.github.juniqlim.realworld.auth.HeaderAuthStringTo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteArticleController {
    private final DeleteArticle deleteArticle;

    public DeleteArticleController(DeleteArticle deleteArticle) {
        this.deleteArticle = deleteArticle;
    }

    @DeleteMapping("/api/articles/{slug}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void articles(@RequestHeader("Authorization") String headerAuthString, @PathVariable("slug") String slug) {
        deleteArticle.delete(slug, HeaderAuthStringTo.userId(headerAuthString));
    }
}
