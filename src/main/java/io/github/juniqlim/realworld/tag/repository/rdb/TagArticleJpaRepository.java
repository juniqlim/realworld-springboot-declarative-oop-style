package io.github.juniqlim.realworld.tag.repository.rdb;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface TagArticleJpaRepository extends JpaRepository<TagArticleEntity, TagArticleEntity.TagArticleId> {
    List<TagArticleEntity> findByIdArticleId(long articleId);
    List<TagArticleEntity> findByIdTagId(long tagId);
    List<TagArticleEntity> findByIdArticleIdIn(List<Long> articleIds);
}
