package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.domain.Comment;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.article.web.CommentResponse;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.User.NoUser;
import io.github.juniqlim.realworld.user.domain.Profile;
import io.github.juniqlim.realworld.user.domain.User;
import io.github.juniqlim.realworld.user.domain.User.Id;
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

    public List<CommentResponse> comments(String slug, io.github.juniqlim.realworld.user.User loginUser) {
        Article article = articleRepository.findBySlug(slug);
        if (loginUser.isExist()) {
            List<Profile> profiles = findUser.findList(userIds(article)).stream()
                .map(user -> user.profile(loginUser.id()))
                .collect(Collectors.toList());
            return comments(article.comments(), profiles);
        }

        List<Profile> profiles = findUser.findList(userIds(article)).stream()
            .map(User::profile)
            .collect(Collectors.toList());
        return comments(article.comments(), profiles);
    }

    private static List<Id> userIds(Article article) {
        return article.comments().stream()
            .map(Comment::userId)
            .collect(Collectors.toList());
    }


    private static List<CommentResponse> comments(List<Comment> comments, List<Profile> profiles) {
        return comments.stream().map(comment -> {
            Profile matchedProfile = profiles.stream().filter(profile -> profile.equalsUserId(comment.userId()))
                .findFirst().orElse(new Profile(new User.Id(0L), "", "", "", false));
            return new CommentResponse(comment, matchedProfile);
        }).collect(Collectors.toList());
    }
}
