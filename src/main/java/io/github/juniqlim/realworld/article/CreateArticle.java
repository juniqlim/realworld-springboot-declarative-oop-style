package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class CreateArticle {
    private final ArticleRepository articleRepository;
    private final FindUser findUser;

    public CreateArticle(ArticleRepository articleRepository, FindUser findUser) {
        this.articleRepository = articleRepository;
        this.findUser = findUser;
    }

    public Article create(Request request) {
        User authorUser = findUser.find(request.authorUserId);
        Article article = new Article(articleRepository.createId(), request.title, request.description, request.body, authorUser.id());
        articleRepository.save(article);
        return article;
    }

    public static class Request {
        private final String title;
        private final String description;
        private final String body;
        private final Id authorUserId;

        public Request(String title, String description, String body, Id authorUserId) {
            this.title = title;
            this.description = description;
            this.body = body;
            this.authorUserId = authorUserId;
        }
    }
}
