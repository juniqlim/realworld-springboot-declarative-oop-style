package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.domain.User;
import java.util.List;

class CreateArticle {
    private final ArticleRepository articleRepository;
    private final FindUser findUser;

    CreateArticle(ArticleRepository articleRepository, FindUser findUser) {
        this.articleRepository = articleRepository;
        this.findUser = findUser;
    }

    Article create(Request request) {
        User author = findUser.find(request.getJwsToken());
        Article article = new Article(request.title, request.description, request.body, author.id(), request.tagList);
        articleRepository.save(article);
        return article;
    }

    static class Request {
        private final String title;
        private final String description;
        private final String body;

        private final String jwsToken;
        private final List<String> tagList;

        public Request(String title, String description, String body, String jwsToken, List<String> tagList) {
            this.title = title;
            this.description = description;
            this.body = body;
            this.jwsToken = jwsToken;
            this.tagList = tagList;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getBody() {
            return body;
        }

        public String getJwsToken() {
            return jwsToken;
        }

        public List<String> getTagList() {
            return tagList;
        }
    }
}
