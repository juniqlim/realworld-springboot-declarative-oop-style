package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteArticle {
    private final ArticleRepository articleRepository;

    public DeleteArticle(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void delete(String slug, Id userId) {
        articleRepository.delete(slug, userId);
    }
}
