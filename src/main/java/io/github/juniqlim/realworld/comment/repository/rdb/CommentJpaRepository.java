package io.github.juniqlim.realworld.comment.repository.rdb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface CommentJpaRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByArticleId(long articleId);
    void deleteByIdAndCommenterUserId(long id, long commenterUserId);
    @Query(value = "call next value for comment_sequence", nativeQuery = true)
    long sequence();
}
