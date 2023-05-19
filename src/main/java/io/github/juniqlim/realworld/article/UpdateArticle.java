package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UpdateArticle {
    private final ArticleRepository articleRepository;

    UpdateArticle(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article update(Request request) {
        String slug = request.getSlug();
        Article article = articleRepository.findBySlugAndUserId(slug, request.getUserId());
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
        private final Id userId;
        private final String slug;
        private final String title;
        private final String description;
        private final String body;

        public Request(Id userId, String slug, String title, String description, String body) {
            this.userId = userId;
            this.slug = slug;
            this.title = title;
            this.description = description;
            this.body = body;
        }

        private Request(Builder builder) {
            this.userId = builder.userId;
            this.slug = builder.slug;
            this.title = builder.title;
            this.description = builder.description;
            this.body = builder.body;
        }

        public static class Builder {
            public Id userId;
            public String slug;
            private String title;
            private String description;
            private String body;

            public Builder userId(Id userId) {
                this.userId = userId;
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

        public Id getUserId() {
            return userId;
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
