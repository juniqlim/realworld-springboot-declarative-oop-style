package io.github.juniqlim.realworld.comment;

import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class DeleteComment {
    private final ArticleRepository articleRepository;
    private final FindUser findUser;

    public DeleteComment(ArticleRepository articleRepository, FindUser findUser) {
        this.articleRepository = articleRepository;
        this.findUser = findUser;
    }

    public void delete(DeleteComment.Request request) {
        User user = findUser.find(request.jwsToken());
        articleRepository.findBySlug(request.slug).deleteComment(request.commentId, user.id());
    }

    public static class Request {
        private final String slug;
        private final long commentId;
        private final String jwsToken;

        public Request(String slug, long commentId, String jwsToken) {
            this.slug = slug;
            this.commentId = commentId;
            this.jwsToken = jwsToken;
        }

        String jwsToken() {
            return jwsToken;
        }
    }
}
