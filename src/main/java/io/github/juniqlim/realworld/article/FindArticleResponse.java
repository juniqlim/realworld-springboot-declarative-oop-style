package io.github.juniqlim.realworld.article;

import static java.util.stream.Collectors.toList;

import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.article.web.ArticleResponse;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.domain.Profile;
import io.github.juniqlim.realworld.user.domain.User;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FindArticleResponse {
    private final ArticleRepository articleRepository;
    private final FindUser findUser;

    public FindArticleResponse(ArticleRepository articleRepository, FindUser findUser) {
        this.articleRepository = articleRepository;
        this.findUser = findUser;
    }

    public Article find(String slug) {
        return articleRepository.findBySlug(slug);
    }

    public List<ArticleResponse> find(Request request) {
        List<Article> articles = articleRepository.findByTagAuthorIdFavoriteUserIdOrderByRegdate(
            request.tag(), request.authorId(findUser), request.favoriteUserId(findUser),
            request.offset(), request.limit());
        User.Id loginUserId = findUser.find(request.jwtToken()).id();
        return articles.stream()
            .map(article -> new ArticleResponse(article, profile(request, article), loginUserId))
            .collect(toList());
    }

    private Profile profile(Request request, Article article) {
        try {
            if (request.jwtToken() == null) {
                return findUser.find(article.authorId()).profile();
            }
            return findUser.find(article.authorId()).profile(findUser.find(request.jwtToken()).id());
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("User not found")) {
                return null;
            }
        }
        return null;
    }

    public static class Request {
        private final String jwtToken;
        private final String tag;
        private final String authorName;
        private final String FavoriteUserName;
        private final int offset;
        private final int limit;

        public Request(String jwtToken, String tag, String authorName, String favoriteUserName, int offset, int limit) {
            this.jwtToken = jwtToken;
            this.tag = tag;
            this.authorName = authorName;
            FavoriteUserName = favoriteUserName;
            this.offset = offset;
            this.limit = limit;
        }

        public static class Builder {
            private String jwtToken;
            private String tag;
            private String authorName;
            private String favoriteUserName;
            private int offset;
            private int limit;

            public Builder jwtToken(String jwtToken) {
                this.jwtToken = jwtToken;
                return this;
            }

            public Builder tag(String tag) {
                this.tag = tag;
                return this;
            }

            public Builder authorName(String authorName) {
                this.authorName = authorName;
                return this;
            }

            public Builder favoriteUserName(String favoriteUserName) {
                this.favoriteUserName = favoriteUserName;
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
                return new Request(jwtToken, tag, authorName, favoriteUserName, offset, limit);
            }
        }

        String jwtToken() {
            return jwtToken;
        }

        String tag() {
            return tag;
        }

        int offset() {
            return offset;
        }

        int limit() {
            return limit;
        }

        User.Id authorId(FindUser findUser) {
            if (authorName == null) {
                return null;
            }
            try {
                return findUser.findByUsername(authorName).id();
            } catch (IllegalArgumentException e) {
                if (e.getMessage().equals("User not found")) {
                    return null;
                }
                throw e;
            }
        }

        User.Id favoriteUserId(FindUser findUser) {
            if (FavoriteUserName == null) {
                return null;
            }
            try {
                return findUser.findByUsername(FavoriteUserName).id();
            } catch (IllegalArgumentException e) {
                if (e.getMessage().equals("User not found")) {
                    return null;
                }
                throw e;
            }
        }
    }
}
