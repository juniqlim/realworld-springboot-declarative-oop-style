package io.github.juniqlim.realworld.comment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import autoparams.AutoSource;
import io.github.juniqlim.realworld.comment.AddComment.Request;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.domain.Comment;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.user.domain.User;
import org.junit.jupiter.params.ParameterizedTest;

class AddCommentTest {
    @ParameterizedTest
    @AutoSource
    void test(Article article) {
        ArticleRepository articleRepository = new ArticleRepository();
        articleRepository.save(article);

        Comment comment = new AddComment(articleRepository).add(new Request(article.slug(), "This is a comment", new User.Id(1)));
        assertEquals("This is a comment", comment.body());
    }
}
