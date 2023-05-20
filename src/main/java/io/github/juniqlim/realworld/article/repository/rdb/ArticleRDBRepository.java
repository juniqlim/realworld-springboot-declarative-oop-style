package io.github.juniqlim.realworld.article.repository.rdb;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.Id.LongId;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

@Repository
class ArticleRDBRepository implements ArticleRepository {
    private final ArticleJpaRepository articleJpaRepository;
    private final ArticleEntityToArticle articleEntityToArticle = new ArticleEntityToArticle();
    private final ArticleToArticleEntity articleToArticleEntity = new ArticleToArticleEntity();

    ArticleRDBRepository(ArticleJpaRepository articleJpaRepository) {
        this.articleJpaRepository = articleJpaRepository;
    }

    @Override
    public void save(Article a) {
        articleJpaRepository.save(articleToArticleEntity.articleEntity(a));
    }

    @Override
    public Article findBySlug(String slug) {
        return articleEntityToArticle.article(articleJpaRepository.findBySlug(slug));
    }

    @Override
    public Article findBySlugAndAuthorUserId(String slug, Id authorUserId) {
        return articleEntityToArticle.article(
            articleJpaRepository.findBySlugAndAuthorUserId(slug, authorUserId.value()));
    }

    @Override
    public void update(String slug, Article article) {
        articleToArticleEntity.articleEntity(article);
    }

    @Override
    public List<Article> findByTagAndAuthorUserIdAndFavoriteUserIdOrderByRegdate(String tag, Id authorUserId, Id favoriteUserId,
        int offset, int limit) {
        return articleEntityToArticle.articles(
            articleJpaRepository.findByAuthorUserIdOrderByCreatedAt(
                authorUserId.value(),
                new OffsetToPage(offset, limit, Sort.by(Direction.DESC, "createdAt")).pageable()
            )
        );
    }

    @Override
    public void delete(String slug, Id userId) {
        articleJpaRepository.deleteBySlugAndAuthorUserId(slug, userId.value());
    }

    @Override
    public List<Article> findByUserIds(List<Id> followUsers, int offset, int limit) {
        return articleEntityToArticle.articles(
            articleJpaRepository.findByAuthorUserIdIn(
                followUsers.stream().map(id -> String.valueOf(id.value())).collect(Collectors.toList()),
                new OffsetToPage(offset, limit, Sort.by(Direction.DESC, "createdAt")).pageable()
            )
        );
    }

    @Override
    public Id createId() {
        return new LongId(articleJpaRepository.sequence());
    }
}
