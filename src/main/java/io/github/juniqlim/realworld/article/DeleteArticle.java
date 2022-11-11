package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.article.repository.ArticleRepository;

class DeleteArticle {
    private final ArticleRepository articleRepository;

    public DeleteArticle(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void delete(String slug) {
        articleRepository.delete(slug);
    }
}
