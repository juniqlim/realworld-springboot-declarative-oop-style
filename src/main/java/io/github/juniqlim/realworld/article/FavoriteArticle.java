package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import org.springframework.stereotype.Service;

@Service
public class FavoriteArticle {
    private final ArticleRepository articleRepository;

    public FavoriteArticle(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article favorite(String slug, Id userId) {
        Article favoritedArticle = articleRepository.findBySlug(slug).favorite(userId);
        articleRepository.update(favoritedArticle.slug(), favoritedArticle);
        return favoritedArticle;
    }
    public Article unFavorite(String slug, Id userId) {
        Article unFavoritedArticle = articleRepository.findBySlug(slug).unFavorite(userId);
        articleRepository.update(unFavoritedArticle.slug(), unFavoritedArticle);
        return unFavoritedArticle;
    }
}
