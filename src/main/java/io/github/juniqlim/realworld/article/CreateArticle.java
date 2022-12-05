package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.domain.Tag;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.user.FindProfile;
import io.github.juniqlim.realworld.user.domain.Profile;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CreateArticle {
    private final ArticleRepository articleRepository;
    private final FindProfile findProfile;
    private final TagUseCase tagUseCase;

    CreateArticle(ArticleRepository articleRepository, FindProfile findProfile, TagUseCase tagUseCase) {
        this.articleRepository = articleRepository;
        this.findProfile = findProfile;
        this.tagUseCase = tagUseCase;
    }

    public ArticleResponse create(Request request) {
        Profile authorProfile = findProfile.profile(request.jwsToken());
        tagUseCase.merges(request.tags());
        Article article = new Article(request.title(), request.description(), request.body(), authorProfile.userId(),
            request.tags().stream()
                .map(Tag::new)
                .collect(Collectors.toList()));
        articleRepository.save(article);
        return new ArticleResponse(article, authorProfile);
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
