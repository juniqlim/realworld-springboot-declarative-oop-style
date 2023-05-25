package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.favorite.FavoriteArticleUseCase;
import org.springframework.stereotype.Service;

@Service
public class DeleteArticle {
    private final ArticleRepository articleRepository;
    private final FavoriteArticleUseCase favoriteArticleUseCase;

    public DeleteArticle(ArticleRepository articleRepository, FavoriteArticleUseCase favoriteArticleUseCase) {
        this.articleRepository = articleRepository;
        this.favoriteArticleUseCase = favoriteArticleUseCase;
    }

    public void delete(String slug, Id userId) {
        Article article = articleRepository.findBySlug(slug);
        favoriteArticleUseCase.deleteArticle(article.id());
        articleRepository.delete(slug, userId);
    }
}
