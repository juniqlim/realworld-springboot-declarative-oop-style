package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.favorite.FavoriteArticleUseCase;
import org.springframework.stereotype.Service;

@Service
public class FavoriteArticle {
    private final ArticleRepository articleRepository;
    private final FavoriteArticleUseCase favoriteArticleUseCase;

    public FavoriteArticle(ArticleRepository articleRepository, FavoriteArticleUseCase favoriteArticleUseCase) {
        this.articleRepository = articleRepository;
        this.favoriteArticleUseCase = favoriteArticleUseCase;
    }

    public Article favorite(String slug, Id userId) {
        Article article = articleRepository.findBySlug(slug);
        if (favoriteArticleUseCase.favoriteArticle(article.id(), userId)) {
            Article favoritedArticle = article.increseFavoritesCount();
            articleRepository.update(favoritedArticle.slug(), favoritedArticle);
            return favoritedArticle;
        }
        return article;
    }

    public Article unFavorite(String slug, Id userId) {
        Article article = articleRepository.findBySlug(slug);
        if (favoriteArticleUseCase.unFavoriteArticle(article.id(), userId)) {
            Article unFavoritedArticle = article.decreaseFavoritesCount();
            articleRepository.update(unFavoritedArticle.slug(), unFavoritedArticle);
            return unFavoritedArticle;
        }
        return article;
    }
}
