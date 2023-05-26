package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.web.ArticleResponse;
import io.github.juniqlim.realworld.favorite.FavoriteArticleUseCase;
import io.github.juniqlim.realworld.tag.TagUseCase;
import io.github.juniqlim.realworld.tag.domain.Tags;
import io.github.juniqlim.realworld.user.FindProfile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class FindArticleResponse {
    private final FindArticle findArticle;
    private final FindProfile findProfile;
    private final TagUseCase tagUseCase;
    private final FavoriteArticleUseCase favoriteArticleUseCase;

    public FindArticleResponse(FindArticle findArticle, FindProfile findProfile, TagUseCase tagUseCase, FavoriteArticleUseCase favoriteArticleUseCase) {
        this.findArticle = findArticle;
        this.findProfile = findProfile;
        this.tagUseCase = tagUseCase;
        this.favoriteArticleUseCase = favoriteArticleUseCase;
    }

    public ArticleResponse find(String slug) {
        Article article = findArticle.find(slug);
        return new ArticleResponse(
            article,
            findProfile.find(article.authorId())
        );
    }

    public ArticleResponse find(String slug, Id loginUserId) {
        Article article = findArticle.find(slug);
        return new ArticleResponse(
            article,
            tagUseCase.findByArticleId(article.id()),
            findProfile.find(article.authorId()),
            favoriteArticleUseCase.isExist(article.id(), loginUserId)
        );
    }

    public List<ArticleResponse> find(Request request) {
        List<Id> articleIds = new ArrayList<>();
        if (request.tag != null && !request.tag.isEmpty()) {
            articleIds.addAll(tagUseCase.findArticleIdsByTag(request.tag));
        }

        if (request.favoriteUserId != null && !request.favoriteUserId.isEmpty()) {
            articleIds.addAll(favoriteArticleUseCase.findArticleIdsByFavoriteUserId(request.favoriteUserId));
        }

        List<Article> articles = findArticle.find(new FindArticle.Request(articleIds, request.authorUserId, request.offset, request.limit));
        List<Id> favoriteArticleIds = favoriteArticleUseCase.findArticleIdsByFavoriteUserId(request.loginUserId);
        List<Tags> tags = tagUseCase.findByArticleIds(articles.stream().map(Article::id).collect(toList()));

        return articles.stream()
            .map(article -> new ArticleResponse(
                article,
                tags.stream()
                    .filter(tag -> tag.articleId().equals(article.id()))
                    .findFirst()
                    .orElse(new Tags(new Id.EmptyId(), new ArrayList<>()))
                    .tags(),
                findProfile.find(article.authorId(), request.loginUserId),
                favoriteArticleIds.contains(article.id()))
            )
            .collect(toList());
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
