package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class FavoriteArticle {
    private final ArticleRepository articleRepository;

    public FavoriteArticle(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article favorite(String slug, User.Id userId) {
        Article article = articleRepository.findBySlug(slug);
        article.favorite(userId);
        articleRepository.update(article.slug(), article);
        return article;
    }
    public Article unFavorite(String slug, User.Id userId) {
        Article article = articleRepository.findBySlug(slug);
        article.unFavorite(userId);
        articleRepository.update(article.slug(), article);
        return article;
    }
}
