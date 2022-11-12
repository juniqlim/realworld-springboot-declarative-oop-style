package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.user.domain.User;

class FavoriteArticle {
    private final ArticleRepository articleRepository;

    public FavoriteArticle(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article favorite(String slug, User.Id userId) {
        Article article = articleRepository.findBySlug(slug);
        article.favorite(userId);
        articleRepository.update(article.getSlug(), article);
        return article;
    }
    public Article UnFavorite(String slug, User.Id userId) {
        Article article = articleRepository.findBySlug(slug);
        article.unFavorite(userId);
        return article;
    }
}
