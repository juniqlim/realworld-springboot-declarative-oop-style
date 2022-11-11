package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;

class FavoriteArticle {
    private final ArticleRepository articleRepository;

    public FavoriteArticle(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article favorite(String slug, String userId) {
        Article article = articleRepository.findBySlug(slug);
        article.favorite(userId);
        return article;
    }
    public Article UnFavorite(String slug, String userId) {
        Article article = articleRepository.findBySlug(slug);
        article.unFavorite(userId);
        return article;
    }
}
