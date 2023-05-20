package io.github.juniqlim.realworld.comment;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.Id.LongId;
import io.github.juniqlim.realworld.comment.domain.Comment;
import io.github.juniqlim.realworld.comment.repository.CommentRepository;
import io.github.juniqlim.realworld.comment.web.CommentResponse;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.domain.Profile;
import io.github.juniqlim.realworld.user.domain.User;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class FindComment {
    private final CommentRepository commentRepository;
    private final FindUser findUser;

    public FindComment(CommentRepository commentRepository, FindUser findUser) {
        this.commentRepository = commentRepository;
        this.findUser = findUser;
    }

    public List<CommentResponse> comments(Id articleId, Id loginUserId) {
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        if (loginUserId.isEmpty()) {
            List<Profile> profiles = findUser.findList(userIds(comments)).stream()
                .map(User::profile)
                .collect(Collectors.toList());
            return comments(comments, profiles);
        }

        List<Profile> profiles = findUser.findList(userIds(comments)).stream()
            .map(user -> user.profile(loginUserId))
            .collect(Collectors.toList());
        return comments(comments, profiles);
    }

    private static List<Id> userIds(List<Comment> comments) {
        return comments.stream()
            .map(Comment::userId)
            .collect(Collectors.toList());
    }


    private static List<CommentResponse> comments(List<Comment> comments, List<Profile> profiles) {
        return comments.stream().map(comment -> {
            Profile matchedProfile = profiles.stream().filter(profile -> profile.equalsUserId(comment.userId()))
                .findFirst().orElse(new Profile(new LongId(0L), "", "", "", false));
            return new CommentResponse(comment, matchedProfile);
        }).collect(Collectors.toList());
    }
}
