package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;

class UpdateArticle {
    private final ArticleRepository articleRepository;

    UpdateArticle(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    Article update(Request request) {
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

    static class Request {
        private final String slug;
        private final String title;
        private final String description;
        private final String body;

        public Request(String slug, String title, String description, String body) {
            this.slug = slug;
            this.title = title;
            this.description = description;
            this.body = body;
        }

        private Request(Builder builder) {
            this.slug = builder.slug;
            this.title = builder.title;
            this.description = builder.description;
            this.body = builder.body;
        }

        public static class Builder {
            public String slug;
            private String title;
            private String description;
            private String body;

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
