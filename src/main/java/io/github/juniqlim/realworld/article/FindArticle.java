package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindArticle {
    private final ArticleRepository articleRepository;

    FindArticle(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article find(String slug) {
        return articleRepository.findBySlug(slug);
    }

    public List<Article> find(Request request) {
        return articleRepository.findByIdInAuthorUserIdAndFavoriteUserIdOrderByRegdate(
            request.articleIds,
            request.authorUserId,
            request.FavoriteUserId,
            request.offset,
            request.limit
        );
    }

    static class Request {
        private final List<Id> articleIds;
        private final Id authorUserId;
        private final Id FavoriteUserId;
        private final int offset;
        private final int limit;

        Request(List<Id> articleIds, Id authorUserId, Id favoriteUserId, int offset, int limit) {
            this.articleIds = articleIds;
            this.authorUserId = authorUserId;
            FavoriteUserId = favoriteUserId;
            this.offset = offset;
            this.limit = limit;
        }
    }
}
