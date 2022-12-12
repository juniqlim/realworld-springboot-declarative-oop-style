package io.github.juniqlim.realworld.article;

import static java.util.stream.Collectors.toList;

import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.article.web.ArticleResponse;
import io.github.juniqlim.realworld.user.FindProfile;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.domain.User;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FindArticleResponse {
    private final ArticleRepository articleRepository;
    private final FindUser findUser;
    private final FindProfile findProfile;

    FindArticleResponse(ArticleRepository articleRepository, FindUser findUser, FindProfile findProfile) {
        this.articleRepository = articleRepository;
        this.findUser = findUser;
        this.findProfile = findProfile;
    }

    public Article find(String slug) {
        return articleRepository.findBySlug(slug);
    }

    public List<ArticleResponse> find(Request request) {
        List<Article> articles = articleRepository.findByTagAuthorIdFavoriteUserIdOrderByRegdate(
            request.tag(), request.authorId(findUser), request.favoriteUserId(findUser),
            request.offset(), request.limit());

        return articles.stream()
            .map(article -> new ArticleResponse(article, findProfile.profile(request.jwtToken(), article.authorId())))
            .collect(toList());
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
            return findUser.findByUsername(authorName).id();
        }

        User.Id favoriteUserId(FindUser findUser) {
            if (FavoriteUserName == null) {
                return null;
            }
            return findUser.findByUsername(FavoriteUserName).id();
        }
    }
}
