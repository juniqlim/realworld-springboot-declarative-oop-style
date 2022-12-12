package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class DeleteArticle {
    private final ArticleRepository articleRepository;

    public DeleteArticle(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void delete(String slug, User.Id userId) {
        articleRepository.delete(slug, userId);
    }
}
