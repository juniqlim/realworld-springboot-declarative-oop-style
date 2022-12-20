package io.github.juniqlim.realworld.article.web;

import io.github.juniqlim.realworld.article.DeleteArticle;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.web.Token;
import java.security.PublicKey;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteArticleController {
    private final DeleteArticle deleteArticle;
    private final FindUser findUser;
    private final PublicKey publicKey;

    public DeleteArticleController(DeleteArticle deleteArticle, FindUser findUser, PublicKey publicKey) {
        this.deleteArticle = deleteArticle;
        this.findUser = findUser;
        this.publicKey = publicKey;
    }

    @DeleteMapping("/api/articles/{slug}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void articles(@RequestHeader("Authorization") String token, @PathVariable("slug") String slug) {
        deleteArticle.delete(slug, findUser.find(new Token(publicKey, token).jwsToken()).id());
    }
}
