package io.github.juniqlim.realworld.article.repository;

import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.article.domain.Article;

import java.util.List;

public interface ArticleRepository {
    void save(Article article);
    Article findBySlug(String slug);
    Article findBySlugAndAuthorUserId(String slug, Id userId);
    void update(String slug, Article article);
    List<Article> findByRequest(Conditions conditions);
    void delete(String slug, Id userId);
    List<Article> findByUserIds(List<Id> followUsers, int offset, int limit);
    Id createId();

    class Conditions {
        private final List<Id> ids;
        private final Id authorUserId;
        private final int offset;
        private final int limit;

        public Conditions(List<Id> ids, Id authorUserId, int offset, int limit) {
            this.ids = ids;
            this.authorUserId = authorUserId;
            this.offset = offset;
            this.limit = limit;
        }

        public List<Id> ids() {
            return ids;
        }

        public Id authorUserId() {
            return authorUserId;
        }

        public int offset() {
            return offset;
        }

        public int limit() {
            return limit;
        }
    }
}
