package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateArticle {
    private final ArticleRepository articleRepository;

    UpdateArticle(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article update(Request request) {
        String slug = request.getSlug();
        Article article = articleRepository.findBySlug(slug);
        if (request.getTitle() != null) {
            article = article.updateTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            article = article.updateDescription(request.getDescription());
        }
        if (request.getBody() != null) {
            article = article.updateBody(request.getBody());
        }
        articleRepository.update(slug, article);
        return article;
    }

    public static class Request {
        private final String jwsToken;
        private final String slug;
        private final String title;
        private final String description;
        private final String body;

        private Request(String jwsToken, String slug, String title, String description, String body) {
            this.jwsToken = jwsToken;
            this.slug = slug;
            this.title = title;
            this.description = description;
            this.body = body;
        }

        private Request(Builder builder) {
            this.jwsToken = builder.jwsToken;
            this.slug = builder.slug;
            this.title = builder.title;
            this.description = builder.description;
            this.body = builder.body;
        }

        public static class Builder {
            public String jwsToken;
            public String slug;
            private String title;
            private String description;
            private String body;

            public Builder jwsToken(String jwsToken) {
                this.jwsToken = jwsToken;
                return this;
            }

            public Builder slug(String slug) {
                this.slug = slug;
                return this;
            }

            public Builder title(String title) {
                this.title = title;
                return this;
            }

            public Builder description(String description) {
                this.description = description;
                return this;
            }

            public Builder body(String body) {
                this.body = body;
                return this;
            }

            public Request build() {
                return new Request(this);
            }
        }

        public String getJwsToken() {
            return jwsToken;
        }

        public String getSlug() {
            return slug;
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
    }
}
