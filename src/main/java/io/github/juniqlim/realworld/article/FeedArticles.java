package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.domain.User;
import java.util.List;

class FeedArticles {
    private final ArticleRepository articleRepository;
    private final FindUser findUser;

    public FeedArticles(ArticleRepository articleRepository, FindUser findUser) {
        this.articleRepository = articleRepository;
        this.findUser = findUser;
    }

    public List<Article> articles(Request request) {
        return articleRepository.findByUserIds(followUsers(request.jwsToken()), request.offset(), request.limit());
    }

    private List<User.Id> followUsers(String jwsToken) {
        return (List<User.Id>) findUser.find(jwsToken).follows();
    }

    static class Request {
        private final String jwsToken;
        private final int offset;
        private final int limit;

        public Request(String jwsToken, int offset, int limit) {
            this.jwsToken = jwsToken;
            this.offset = offset;
            this.limit = limit;
        }

        public String jwsToken() {
            return jwsToken;
        }

        public int offset() {
            return offset;
        }

        public int limit() {
            return limit;
        }
    }
}
