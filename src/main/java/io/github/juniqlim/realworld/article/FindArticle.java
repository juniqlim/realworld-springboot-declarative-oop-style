package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;

class FindArticle {
    private final ArticleRepository articleRepository;

    public FindArticle(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article find(String slug) {
        return articleRepository.findBySlug(slug);
    }
}
