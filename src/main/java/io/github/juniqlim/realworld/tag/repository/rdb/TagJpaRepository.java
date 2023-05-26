package io.github.juniqlim.realworld.tag.repository.rdb;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface TagJpaRepository extends JpaRepository<TagEntity, Long> {
    List<TagEntity> findByTagIn(List<String> tags);
    List<TagEntity> findByIdIn(List<Long> ids);
    TagEntity findByTag(String tag);
}
