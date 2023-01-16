package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.domain.Comment;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.article.web.CommentResponse;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.domain.Profile;
import io.github.juniqlim.realworld.user.domain.User;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class FindComment {
    private final ArticleRepository articleRepository;
    private final FindUser findUser;

    FindComment(ArticleRepository articleRepository, FindUser findUser) {
        this.articleRepository = articleRepository;
        this.findUser = findUser;
    }

    public List<CommentResponse> comments(String slug) {
        Article article = articleRepository.findBySlug(slug);
        List<Comment> comments = article.comments();
        List<User.Id> userIds = comments.stream()
            .map(Comment::userId)
            .collect(Collectors.toList());
        List<Profile> profiles = findUser.findList(userIds).stream()
            .map(User::profile)
            .collect(Collectors.toList());

        return comments.stream()
            .map(comment -> {
                Profile matchedProfile = profiles.stream()
                    .filter(profile -> profile.equalsUserId(comment.userId()))
                    .findFirst().orElse(new Profile(new User.Id(0L), "", "", "", false));
                return new CommentResponse(comment, matchedProfile);
            }).collect(Collectors.toList());
    }

    public List<CommentResponse> comments(String slug, String jwsToken) {
        User.Id finderId = findUser.find(jwsToken).id();
        Article article = articleRepository.findBySlug(slug);
        List<Comment> comments = article.comments();
        List<User.Id> userIds = comments.stream()
            .map(Comment::userId)
            .collect(Collectors.toList());
        List<Profile> profiles = findUser.findList(userIds).stream()
            .map(user -> user.profile(finderId))
            .collect(Collectors.toList());

        return comments.stream()
            .map(comment -> {
                Profile matchedProfile = profiles.stream()
                    .filter(profile -> profile.equalsUserId(comment.userId()))
                    .findFirst().orElse(new Profile(new User.Id(0L), "", "", "", false));
                return new CommentResponse(comment, matchedProfile);
            }).collect(Collectors.toList());
    }
}
