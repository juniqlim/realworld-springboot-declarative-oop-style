package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.web.ArticleResponse;
import io.github.juniqlim.realworld.tag.TagUseCase;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.domain.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class FindArticleResponse {
    private final FindArticle findArticle;
    private final TagUseCase tagUseCase;
    private final FindUser findUser;

    public FindArticleResponse(FindArticle findArticle, TagUseCase tagUseCase, FindUser findUser) {
        this.findArticle = findArticle;
        this.tagUseCase = tagUseCase;
        this.findUser = findUser;
    }

    public List<ArticleResponse> find(Request request) {
        List<Id> articleIds = new ArrayList<>();
        if (request.tag != null && !request.tag.isEmpty()) {
            articleIds.addAll(tagUseCase.findArticleIdsByTag(request.tag));
        }

        List<Article> articles = findArticle.find(new FindArticle.Request(articleIds, request.authorUserId, request.favoriteUserId, request.offset, request.limit));

        return articles.stream()
            .map(article -> new ArticleResponse(article, new ArrayList<>(), profile(request, article), request.loginUserId))
            .collect(toList());
    }

    private Profile profile(Request request, Article article) {
        try {
            if (request.loginUserId.isEmpty()) {
                return findUser.find(article.authorId()).profile();
            }
            return findUser.find(article.authorId()).profile(request.loginUserId);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("User not found")) {
                return null;
            }
        }
        return null;
    }

    public static class Request {
        private final Id loginUserId;
        private final String tag;
        private final Id authorUserId;
        private final Id favoriteUserId;
        private final int offset;
        private final int limit;

        public Request(Id loginUserId, String tag, Id authorUserId, Id favoriteUserId, int offset, int limit) {
            this.loginUserId = loginUserId;
            this.tag = tag;
            this.authorUserId = authorUserId;
            this.favoriteUserId = favoriteUserId;
            this.offset = offset;
            this.limit = limit;
        }

        public static class Builder {
            private Id loginUserId;
            private String tag;
            private Id authorUserId;
            private Id favoriteUserId;
            private int offset;
            private int limit;

            public Builder loginUserId(Id loginUserId) {
                this.loginUserId = loginUserId;
                return this;
            }

            public Builder tag(String tag) {
                this.tag = tag;
                return this;
            }

            public Builder authorUserId(Id authorUserId) {
                this.authorUserId = authorUserId;
                return this;
            }

            public Builder favoriteUserId(Id favoriteUserId) {
                this.favoriteUserId = favoriteUserId;
                return this;
            }

            public Builder offset(int offset) {
                this.offset = offset;
                return this;
            }

            public Builder limit(int limit) {
                this.limit = limit;
                return this;
            }

            public Request build() {
                return new Request(nullIsEmptyId(loginUserId), tag, nullIsEmptyId(authorUserId), nullIsEmptyId(favoriteUserId), offset, limit);
            }

            private Id nullIsEmptyId(Id id) {
                if (id == null) {
                    return new Id.EmptyId();
                }
                return id;
            }
        }
    }
}
