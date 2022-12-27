package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.domain.Comment;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.domain.Profile;
import io.github.juniqlim.realworld.user.domain.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

class FindComment {
    private final ArticleRepository articleRepository;
    private final FindUser findUser;

    FindComment(ArticleRepository articleRepository, FindUser findUser) {
        this.articleRepository = articleRepository;
        this.findUser = findUser;
    }

    public List<ResultComment> comments(String slug, String jwsToken) {
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
