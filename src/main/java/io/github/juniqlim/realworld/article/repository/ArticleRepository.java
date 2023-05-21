package io.github.juniqlim.realworld.article.repository;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.article.domain.Article;

import java.util.List;

public interface ArticleRepository {
    void save(Article article);
    Article findBySlug(String slug);
    Article findBySlugAndAuthorUserId(String slug, Id userId);
    void update(String slug, Article article);
    List<Article> findAuthorUserIdAndFavoriteUserIdOrderByRegdate(Id authorId, Id favoriteUserId, int offset, int limit);
    void delete(String slug, Id userId);
    List<Article> findByUserIds(List<Id> followUsers, int offset, int limit);
    Id createId();
}
