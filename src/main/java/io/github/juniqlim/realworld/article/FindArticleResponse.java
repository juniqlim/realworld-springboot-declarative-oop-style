package io.github.juniqlim.realworld.article;

import static java.util.stream.Collectors.toList;

import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.article.web.ArticleResponse;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.domain.Profile;
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
        List<Article> articles = articleRepository.findByTagAuthorFavoriteUserOrderByRegdate(
            request.tag(), request.author(), request.favoriteUser(),
            request.offset(), request.limit());

        return articles.stream()
            .map(article -> new ArticleResponse(article, profile(request, article), request.user))
            .collect(toList());
    }

    private Profile profile(Request request, Article article) {
        try {
            if (request.user().isExist()) {
                return findUser.find(article.authorId()).profile(request.user().id());
            }
            return findUser.find(article.authorId()).profile();
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("User not found")) {
                return null;
            }
        }
        return null;
    }

    public static class Request {
        private final io.github.juniqlim.realworld.user.User user;
        private final String tag;
        private final io.github.juniqlim.realworld.user.User author;
        private final io.github.juniqlim.realworld.user.User favoriteUser;
        private final int offset;
        private final int limit;

        public Request(io.github.juniqlim.realworld.user.User user, String tag,
            io.github.juniqlim.realworld.user.User author, io.github.juniqlim.realworld.user.User favoriteUser,
            int offset, int limit) {
            this.user = user;
            this.tag = tag;
            this.author = author;
            this.favoriteUser = favoriteUser;
            this.offset = offset;
            this.limit = limit;
        }



        io.github.juniqlim.realworld.user.User user() {
            return user;
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

        io.github.juniqlim.realworld.user.User author() {
            return author;
        }

        io.github.juniqlim.realworld.user.User favoriteUser() {
            return favoriteUser;
        }

        public static class Builder {
            private io.github.juniqlim.realworld.user.User user;
            private String tag;
            private io.github.juniqlim.realworld.user.User author;
            private io.github.juniqlim.realworld.user.User favoriteUser;
            private int offset;
            private int limit;

            public Builder user(io.github.juniqlim.realworld.user.User user) {
                this.user = user;
                return this;
            }

            public Builder tag(String tag) {
                this.tag = tag;
                return this;
            }

            public Builder author(io.github.juniqlim.realworld.user.User authorName) {
                this.author = authorName;
                return this;
            }

            public Builder favoriteUser(io.github.juniqlim.realworld.user.User favoriteUserName) {
                this.favoriteUser = favoriteUserName;
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
                return new Request(user, tag, author, favoriteUser, offset, limit);
            }
        }
    }
}
