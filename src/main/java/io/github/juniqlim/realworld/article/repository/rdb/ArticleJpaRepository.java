package io.github.juniqlim.realworld.article.repository.rdb;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface ArticleJpaRepository extends JpaRepository<ArticleEntity, Long> {
    ArticleEntity findBySlug(String slug);
    ArticleEntity findBySlugAndAuthorUserId(String slug, long authorUserId);
    List<ArticleEntity> findByIdInAndAuthorUserIdOrderByCreatedAt(List<Long> ids, long authorUserId, Pageable pageable);
    List<ArticleEntity> findAuthorUserIdOrderByCreatedAt(long authorUserId, Pageable pageable);
    List<ArticleEntity> findByAuthorUserIdIn(List<Long> authorUserIds, Pageable pageable);
    List<ArticleEntity> findByIdInOrderByCreatedAt(List<Long> ids, Pageable pageable);
    List<ArticleEntity> findByOrderByCreatedAt(PageRequest pageable);
    void deleteBySlugAndAuthorUserId(String slug, long authorUserId);
    @Query(value = "call next value for article_sequence", nativeQuery = true)
    long sequence();
}
