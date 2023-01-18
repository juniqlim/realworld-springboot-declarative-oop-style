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
        return articleRepository.findByUserIds(followUsers(request.loginUser()), request.offset(), request.limit()).stream()
            .map(article -> new ArticleResponse(article, profile(request.loginUser(), article), request.loginUser()))
            .collect(toList());
    }

    private List<User.Id> followUsers(io.github.juniqlim.realworld.user.User loginUser) {
        return (List<User.Id>) findUser.find(loginUser.id()).follows();
    }

    private Profile profile(io.github.juniqlim.realworld.user.User loginUser, Article article) {
        try {
            if (loginUser.isExist()) {
                return findUser.find(article.authorId()).profile(loginUser.id());
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
        private final io.github.juniqlim.realworld.user.User loginUser;
        private final int offset;
        private final int limit;

        public Request(io.github.juniqlim.realworld.user.User loginUser, int offset, int limit) {
            this.loginUser = loginUser;
            this.offset = offset;
            this.limit = limit;
        }

        public io.github.juniqlim.realworld.user.User loginUser() {
            return loginUser;
        }

        public int offset() {
            return offset;
        }

        public int limit() {
            return limit;
        }
    }
}
