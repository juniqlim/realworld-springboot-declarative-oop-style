package io.github.juniqlim.realworld.article.repository.rdb;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.Id.LongId;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
class ArticleRDBRepository implements ArticleRepository {
    private final ArticleJpaRepository articleJpaRepository;
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
        return articleToArticleEntity.article(articleJpaRepository.findBySlug(slug));
    }

    @Override
    public Article findBySlugAndAuthorUserId(String slug, Id authorUserId) {
        return articleToArticleEntity.article(
            articleJpaRepository.findBySlugAndAuthorUserId(slug, authorUserId.value()));
    }

    @Override
    public void update(String slug, Article article) {
        articleJpaRepository.save(articleToArticleEntity.articleEntity(article));
    }

    @Override
    public List<Article> findByRequest(Conditions c) {
        if (!c.ids().isEmpty() && !c.authorUserId().isEmpty()) {
            return articleToArticleEntity.articles(
                articleJpaRepository.findByIdInAndAuthorUserIdOrderByCreatedAt(
                    c.ids().stream()
                        .map(id -> ((LongId) id).value())
                        .collect(Collectors.toList()),
                    c.authorUserId().value(),
                    new OffsetToPage(c.offset(), c.limit(), Sort.by(Direction.DESC, "createdAt"))
                        .pageable()
                )
            );
        }
        if (!c.ids().isEmpty() && c.authorUserId().isEmpty()) {
            return articleToArticleEntity.articles(
                articleJpaRepository.findByIdInOrderByCreatedAt(
                    c.ids().stream()
                        .map(id -> ((LongId) id).value())
                        .collect(Collectors.toList()),
                    new OffsetToPage(c.offset(), c.limit(), Sort.by(Direction.DESC, "createdAt"))
                        .pageable()
                )
            );
        }
        if (c.ids().isEmpty() && !c.authorUserId().isEmpty()) {
            return articleToArticleEntity.articles(
                articleJpaRepository.findAuthorUserIdOrderByCreatedAt(
                    c.authorUserId().value(),
                    new OffsetToPage(c.offset(), c.limit(), Sort.by(Direction.DESC, "createdAt"))
                        .pageable()
                )
            );
        }
        return articleToArticleEntity.articles(
            articleJpaRepository.findByOrderByCreatedAt(
                new OffsetToPage(c.offset(), c.limit(), Sort.by(Direction.DESC, "createdAt"))
                    .pageable()
            )
        );
    }

    @Override
    public void delete(String slug, Id userId) {
        articleJpaRepository.deleteBySlugAndAuthorUserId(slug, userId.value());
    }

    @Override
    public List<Article> findByUserIds(List<Id> followUsers, int offset, int limit) {
        return articleToArticleEntity.articles(
            articleJpaRepository.findByAuthorUserIdIn(
                followUsers.stream()
                    .map(id -> ((LongId)id).value())
                    .collect(Collectors.toList()),
                new OffsetToPage(offset, limit, Sort.by(Direction.DESC, "createdAt"))
                    .pageable()
            )
        );
    }

    @Override
    public Id createId() {
        return new LongId(articleJpaRepository.sequence());
    }
}
