package io.github.juniqlim.realworld.comment.repository.rdb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(
    name = "COMMENT_SEQ_GENERATOR",
    sequenceName = "commentSequence",
    allocationSize = 1
)
class CommentSequenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENT_SEQ_GENERATOR")
    private Long id;
}
