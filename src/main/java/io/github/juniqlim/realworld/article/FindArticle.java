package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.user.FindUser;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FindArticle {
    private final ArticleRepository articleRepository;
    private final FindUser findUser;

    FindArticle(ArticleRepository articleRepository, FindUser findUser) {
        this.articleRepository = articleRepository;
        this.findUser = findUser;
    }

    public Article find(String slug) {
        return articleRepository.findBySlug(slug);
    }

    public List<Article> find(Request request) {
        return articleRepository.findByTagAndAuthorUserIdAndFavoriteUserIdOrderByRegdate(
            request.tag,
            authorUserId(request.authorName),
            favoriteUserId(request.FavoriteUserName),
            request.offset, request.limit);
    }

    private Id authorUserId(String authorName) {
        if (authorName == null) {
            return new Id.EmptyId();
        }
        return findUser.findByUsername(authorName).id();
    }

    private Id favoriteUserId(String favoriteUserName) {
        if (favoriteUserName == null) {
            return new Id.EmptyId();
        }
        return findUser.findByUsername(favoriteUserName).id();
    }

    static class Request {
        private final String tag;
        private final String authorName;
        private final String FavoriteUserName;
        private final int offset;
        private final int limit;

        public Request(String tag, String authorName, String favoriteUserName, int offset, int limit) {
            this.tag = tag;
            this.authorName = authorName;
            FavoriteUserName = favoriteUserName;
            this.offset = offset;
            this.limit = limit;
        }
    }
}
