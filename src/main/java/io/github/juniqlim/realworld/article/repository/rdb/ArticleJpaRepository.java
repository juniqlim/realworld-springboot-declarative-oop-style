package io.github.juniqlim.realworld.article.repository.rdb;

import io.github.juniqlim.realworld.Id;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface ArticleJpaRepository extends JpaRepository<ArticleEntity, Long> {
    ArticleEntity findBySlug(String slug);
    ArticleEntity findBySlugAndAuthorUserId(String slug, Id authorUserId);
    List<ArticleEntity> findByIdInAuthorUserIdOrderByCreatedAt(List<Id> ids, String authorUserId, Pageable pageable);
    void deleteBySlugAndAuthorUserId(String slug, Object value);
    List<ArticleEntity> findByAuthorUserIdIn(List<String> authorUserIds, Pageable pageable);
    @Query(value = "call next value for article_sequence", nativeQuery = true)
    long sequence();
}
