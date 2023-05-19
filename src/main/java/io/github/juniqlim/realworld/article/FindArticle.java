package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.domain.User;
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
        return articleRepository.findByTagAuthorIdFavoriteUserIdOrderByRegdate(request.tag(), request.authorId(findUser), request.favoriteUserId(findUser),
            request.offset(), request.limit());
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

        public String tag() {
            return tag;
        }

        public int offset() {
            return offset;
        }

        public int limit() {
            return limit;
        }

        public Id authorId(FindUser findUser) {
            if (authorName == null) {
                return null;
            }
            return findUser.findByUsername(authorName).id();
        }

        public Id favoriteUserId(FindUser findUser) {
            if (FavoriteUserName == null) {
                return null;
            }
            return findUser.findByUsername(FavoriteUserName).id();
        }
    }
}
