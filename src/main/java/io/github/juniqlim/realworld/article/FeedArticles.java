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
public class FeedArticles {
    private final ArticleRepository articleRepository;
    private final FindUser findUser;

    public FeedArticles(ArticleRepository articleRepository, FindUser findUser) {
        this.articleRepository = articleRepository;
        this.findUser = findUser;
    }

    public List<ArticleResponse> articles(Request request) {
        User.Id loginUserId = findUser.find(request.jwsToken()).id();
        return articleRepository.findByUserIds(followUsers(request.jwsToken()), request.offset(), request.limit()).stream()
            .map(article -> new ArticleResponse(article, profile(request.jwsToken(), article), loginUserId))
            .collect(toList());
    }

    private List<User.Id> followUsers(String jwsToken) {
        return (List<User.Id>) findUser.find(jwsToken).follows();
    }

    private Profile profile(String jwtToken, Article article) {
        try {
            if (jwtToken == null) {
                return findUser.find(article.authorId()).profile();
            }
            return findUser.find(article.authorId()).profile(findUser.find(jwtToken).id());
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("User not found")) {
                return null;
            }
        }
        return null;
    }

    public static class Request {
        private final String jwsToken;
        private final int offset;
        private final int limit;

        public Request(String jwsToken, int offset, int limit) {
            this.jwsToken = jwsToken;
            this.offset = offset;
            this.limit = limit;
        }

        public String jwsToken() {
            return jwsToken;
        }

        public int offset() {
            return offset;
        }

        public int limit() {
            return limit;
        }
    }
}
