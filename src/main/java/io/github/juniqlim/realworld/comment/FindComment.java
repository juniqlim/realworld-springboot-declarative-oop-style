package io.github.juniqlim.realworld.comment;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.Id.LongId;
import io.github.juniqlim.realworld.comment.domain.Comment;
import io.github.juniqlim.realworld.comment.repository.CommentRepository;
import io.github.juniqlim.realworld.comment.web.CommentResponse;
import io.github.juniqlim.realworld.user.FindProfile;
import io.github.juniqlim.realworld.user.domain.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindComment {
    private final CommentRepository commentRepository;
    private final FindProfile findProfile;

    public FindComment(CommentRepository commentRepository, FindProfile findProfile) {
        this.commentRepository = commentRepository;
        this.findProfile = findProfile;
    }

    public List<CommentResponse> comments(Id articleId, Id loginUserId) {
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        return comments(
            comments,
            findProfile.find(userIds(comments), loginUserId)
        );
    }

    private static List<Id> userIds(List<Comment> comments) {
        return comments.stream()
            .map(Comment::userId)
            .collect(Collectors.toList());
    }


    private static List<CommentResponse> comments(List<Comment> comments, List<Profile> profiles) {
        return comments.stream()
            .map(comment -> new CommentResponse(comment, matchedProfile(profiles, comment)))
            .collect(Collectors.toList());
    }

    private static Profile matchedProfile(List<Profile> profiles, Comment comment) {
        return profiles.stream()
            .filter(profile -> profile.equalsUserId(comment.userId()))
            .findFirst()
            .orElse(new Profile(new LongId(0L), "", "", "", false));
    }
}
