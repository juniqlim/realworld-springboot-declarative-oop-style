package io.github.juniqlim.realworld.article.web;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.user.FindUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
class RappingArticle {
    private final FindUser findUser;

    RappingArticle(FindUser findUser) {
        this.findUser = findUser;
    }

    ArticleResponse value(Article article, Id loginUserId) {
        return new ArticleResponse(
            article,
            new ArrayList<>(),
            findUser.find(article.authorId()).profile(),
            loginUserId
        );
    }
}
