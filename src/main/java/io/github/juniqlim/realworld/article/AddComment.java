package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.domain.Comment;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;

class AddComment {
    private final ArticleRepository articleRepository;
    public AddComment(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Comment add(Request request) {
        Comment comment = request.comment(articleRepository.findCommentSequence());
        articleRepository.findBySlug(request.slug).addComment(comment);
        return comment;
    }

    static class Request {
        private final String slug;
        private final String comment;
        private final String userId;

        public Request(String slug, String comment, String userId) {
            this.slug = slug;
            this.comment = comment;
            this.userId = userId;
        }

        public Comment comment(long commentId) {
            return new Comment(commentId, comment, userId);
        }
    }
}
