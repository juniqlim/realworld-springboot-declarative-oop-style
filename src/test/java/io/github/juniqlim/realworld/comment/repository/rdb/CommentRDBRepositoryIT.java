package io.github.juniqlim.realworld.comment.repository.rdb;

import io.github.juniqlim.realworld.Fixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@Import(CommentRDBRepository.class)
class CommentRDBRepositoryIT {
    @Autowired CommentRDBRepository commentRDBRepository;

    @Test
    void test() {
        commentRDBRepository.save(Fixture.JAKE_ARTICLE_COMMENT);

        assertEquals(
            Fixture.JAKE_ARTICLE_COMMENT.body(),
            commentRDBRepository.findByArticleId(Fixture.JAKE_ARTICLE_COMMENT.articleId()).get(0).body()
        );
    }
}