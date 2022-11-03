package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import java.util.List;

class CreateArticle {
    private final ArticleRepository articleRepository;

    public CreateArticle(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    Article create(Request request) {
        return new Article(request.title, request.description, request.body, request.tagList);
    }

    static class Request {
        private final String title;
        private final String description;
        private final String body;
        private final List<String> tagList;

        public Request(String title, String description, String body, List<String> tagList) {
            this.title = title;
            this.description = description;
            this.body = body;
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

        public List<String> getTagList() {
            return tagList;
        }
    }
}
