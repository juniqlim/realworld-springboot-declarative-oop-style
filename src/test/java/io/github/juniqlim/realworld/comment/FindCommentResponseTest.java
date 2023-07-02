package io.github.juniqlim.realworld.comment;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.comment.repository.CommentRepository;
import io.github.juniqlim.realworld.comment.web.CommentResponse;
import io.github.juniqlim.realworld.user.FindProfile;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.FollowUser;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindCommentResponseTest {
    FindCommentResponse findCommentResponse;

    @BeforeEach
    void setUp() {
        CommentRepository.ListRepository commentRepository = new CommentRepository.ListRepository();
        commentRepository.save(Fixture.MINK_ARTICLE_COMMENT1);
        commentRepository.save(Fixture.MINK_ARTICLE_COMMENT2);
        findCommentResponse = new FindCommentResponse(
            new FindComment(commentRepository),
            new FindProfile(
                new FindUser(new UserRepository.UserArrayListRepository()),
                new FollowUser(new UserRepository.UserArrayListRepository())
            )
        );
    }

    @Test
    void test() {
        List<CommentResponse> comments = findCommentResponse.comments(Fixture.MINK_ARTICLE.id(), Fixture.MINK.id());

        assertEquals(2, comments.size());
    }
}