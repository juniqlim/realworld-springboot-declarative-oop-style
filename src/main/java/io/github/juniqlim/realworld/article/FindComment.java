package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.domain.Comment;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.user.FindProfile;
import io.github.juniqlim.realworld.user.domain.Profile;
import io.github.juniqlim.realworld.user.domain.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

class FindComment {
    private final ArticleRepository articleRepository;
    private final FindProfile findProfile;

    public FindComment(ArticleRepository articleRepository, FindProfile findProfile) {
        this.articleRepository = articleRepository;
        this.findProfile = findProfile;
    }

    public List<ResultComment> comments(String slug, String jwsToken) {
        Article article = articleRepository.findBySlug(slug);
        List<Comment> comments = article.comments();
        List<User.Id> userIds = comments.stream()
            .map(Comment::userId)
            .collect(Collectors.toList());
        List<Profile> profiles = findProfile.profiles(jwsToken, userIds);

        return comments.stream()
            .map(comment -> {
                Profile matchedProfile = profiles.stream()
                    .filter(profile -> profile.equalsUserId(comment.userId()))
                    .findFirst().orElse(new Profile(new User.Id(0L), "", "", "", false));
                return new ResultComment(comment, matchedProfile);
            }).collect(Collectors.toList());
    }

    static class ResultComment {
        private final long id;
        private final String body;
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;
        private final Profile author;

        public ResultComment(Comment comment, Profile profile) {
            this(comment.id(), comment.body(), comment.createdAt(), comment.updatedAt(), profile);
        }

        ResultComment(long id, String body, LocalDateTime createdAt, LocalDateTime updatedAt, Profile author) {
            this.id = id;
            this.body = body;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.author = author;
        }

        long getId() {
            return id;
        }

        String getBody() {
            return body;
        }

        LocalDateTime getCreatedAt() {
            return createdAt;
        }

        LocalDateTime getUpdatedAt() {
            return updatedAt;
        }

        Profile getAuthor() {
            return author;
        }
    }
}
