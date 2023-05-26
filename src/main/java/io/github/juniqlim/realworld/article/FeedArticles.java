package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.article.web.ArticleResponse;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.domain.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class FeedArticles {
    private final ArticleRepository articleRepository;
    private final FindUser findUser;

    public FeedArticles(ArticleRepository articleRepository, FindUser findUser) {
        this.articleRepository = articleRepository;
        this.findUser = findUser;
    }

    public List<ArticleResponse> articles(Request request) {
        return articleRepository.findByUserIds(followUsers(request.loginUserId), request.offset, request.limit).stream()
            .map(article -> new ArticleResponse(article, new ArrayList<>(), profile(request.loginUserId, article), false))
            .collect(toList());
    }

    private List<Id> followUsers(Id loginUserId) {
        return (List<Id>) findUser.find(loginUserId).follows();
    }

    private Profile profile(Id loginUserId, Article article) {
        try {
            if (loginUserId.isEmpty()) {
                return findUser.find(article.authorId()).profile();
            }
            return findUser.find(article.authorId()).profile(loginUserId);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("User not found")) {
                return null;
            }
        }
        return null;
    }

    public static class Request {
        private final Id loginUserId;
        private final int offset;
        private final int limit;

        public Request(Id loginUserId, int offset, int limit) {
            this.loginUserId = loginUserId;
            this.offset = offset;
            this.limit = limit;
        }
    }
}
