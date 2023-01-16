package io.github.juniqlim.realworld.article.web;

import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.domain.User;
import org.springframework.stereotype.Service;

@Service
class RappingArticle {
    private final FindUser findUser;

    RappingArticle(FindUser findUser) {
        this.findUser = findUser;
    }

    ArticleResponse value(Article article) {
        return new ArticleResponse(
            article,
            findUser.find(article.authorId()).profile()
        );
    }

    ArticleResponse value(Article article, String loginUserJwsToken) {
        return value(article, findUser.find(loginUserJwsToken).id());
    }

    ArticleResponse value(Article article, User.Id loginUserId) {
        return new ArticleResponse(
            article,
            findUser.find(article.authorId()).profile(),
            loginUserId
        );
    }
}
