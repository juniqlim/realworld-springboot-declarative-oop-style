package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.tag.TagUseCase;
import io.github.juniqlim.realworld.tag.domain.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
class CreateArticleAndTag {
    private final CreateArticle createArticle;
    private final TagUseCase tagUseCase;

    public CreateArticleAndTag(CreateArticle createArticle, TagUseCase tagUseCase) {
        this.createArticle = createArticle;
        this.tagUseCase = tagUseCase;
    }

    public Response create(Request request) {
        Article article = createArticle.create(
            new CreateArticle.Request(request.title, request.description, request.body, request.authorUserId)
        );
        List<Tag> tags = tagUseCase.merge(new TagUseCase.Request(article.id(), request.tags));
        return new Response(
            article,
            tags
        );
    }

    public static class Request {
        private final String title;
        private final String description;
        private final String body;
        private final Id authorUserId;
        private final List<String> tags;

        public Request(String title, String description, String body, Id authorUserId, List<String> tags) {
            this.title = title;
            this.description = description;
            this.body = body;
            this.authorUserId = authorUserId;
            this.tags = tags;
        }
    }

    public static class Response {
        private final Article article;
        private final List<Tag> tags;

        public Response(Article article, List<Tag> tags) {
            this.article = article;
            this.tags = tags;
        }

        public Article article() {
            return article;
        }

        public List<Tag> tags() {
            return tags;
        }
    }
}
