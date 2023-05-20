package io.github.juniqlim.realworld.article.repository.rdb;

import io.github.juniqlim.realworld.Id;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

interface ArticleJpaRepository extends JpaRepository<ArticleEntity, Long> {
    ArticleEntity findBySlug(String slug);
    ArticleEntity findBySlugAndAuthorUserId(String slug, Id authorUserId);
    List<ArticleEntity> findByAuthorUserIdOrderByCreatedAt(String authorUserId, Pageable pageable);
    void deleteBySlugAndAuthorUserId(String slug, Object value);
    List<ArticleEntity> findByAuthorUserIdIn(List<String> authorUserIds, Pageable pageable);
}
