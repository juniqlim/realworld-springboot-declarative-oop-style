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
        return new Article(request.title, request.description, request.body, request.authorId, request.tagList);
    }

    static class Request {
        private final String title;
        private final String description;
        private final String body;

        private final String authorId;
        private final List<String> tagList;

        public Request(String title, String description, String body, String authorId, List<String> tagList) {
            this.title = title;
            this.description = description;
            this.body = body;
            this.authorId = authorId;
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

        public String getAuthorId() {
            return authorId;
        }

        public List<String> getTagList() {
            return tagList;
        }
    }
}
