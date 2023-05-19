package io.github.juniqlim.realworld.comment;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.Id.LongId;
import io.github.juniqlim.realworld.comment.DeleteComment.Request;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.User;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import org.junit.jupiter.api.Test;

class DeleteCommentTest {
    @Test
    void test() {
        UserRepository userRepository = new UserRepository.Collection();
        userRepository.save(Fixture.JUNIQ);

        new DeleteComment(Fixture.COMMENT_REPOSITORY, new FindUser(userRepository))
            .delete(new Request(new LongId(3), Fixture.JUNIQ.token()));
        new DeleteComment(Fixture.COMMENT_REPOSITORY, new FindUser(userRepository))
            .delete(new Request(new LongId(4), Fixture.JUNIQ.token()));

        new FindComment(Fixture.COMMENT_REPOSITORY, new FindUser(userRepository))
            .comments(Fixture.MINK_ARTICLE.id(), new User.UserByToken(new FindUser(userRepository), null));
    }
}
