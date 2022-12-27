package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.domain.Tag;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CreateArticle {
    private final ArticleRepository articleRepository;
    private final FindUser findUser;
    private final TagUseCase tagUseCase;

    public CreateArticle(ArticleRepository articleRepository, FindUser findUser, TagUseCase tagUseCase) {
        this.articleRepository = articleRepository;
        this.findUser = findUser;
        this.tagUseCase = tagUseCase;
    }

    public Article create(Request request) {
        User authorUser = findUser.find(request.jwsToken());
        tagUseCase.merges(request.tags());
        Article article = new Article(request.title(), request.description(), request.body(), authorUser.id(),
            request.tags().stream()
                .map(Tag::new)
                .collect(Collectors.toList()));
        articleRepository.save(article);
        return article;
    }

    public static class Request {
        private final String title;
        private final String description;
        private final String body;
        private final String jwsToken;
        private final List<String> tags;

        public Request(String title, String description, String body, String jwsToken, List<String> tags) {
            this.title = title;
            this.description = description;
            this.body = body;
            this.jwsToken = jwsToken;
            this.tags = tags;
        }

        String title() {
            return title;
        }

        String description() {
            return description;
        }

        String body() {
            return body;
        }

        String jwsToken() {
            return jwsToken;
        }

        List<String> tags() {
            if (tags == null) {
                return new ArrayList<>();
            }
            return tags;
        }
    }
}
