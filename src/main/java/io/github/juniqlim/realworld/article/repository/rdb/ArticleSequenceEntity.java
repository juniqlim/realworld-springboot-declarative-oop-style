package io.github.juniqlim.realworld.article.repository.rdb;

import javax.persistence.*;

@Entity
@SequenceGenerator(
    name = "ARTICLE_SEQ_GENERATOR",
    sequenceName = "articleSequence",
    allocationSize = 1
)
class ArticleSequenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARTICLE_SEQ_GENERATOR")
    private Long id;
}
