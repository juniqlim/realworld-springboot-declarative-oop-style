package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
class FindArticle {
    private final ArticleRepository articleRepository;

    public FindArticle(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article find(String slug) {
        return articleRepository.findBySlug(slug);
    }

    public List<Article> find(Request request) {
        return articleRepository.findByTagAuthorIdOrderByRegdate(request.getTag(), request.getAuthorName(),
                request.getOffset(), request.getLimit());
    }

    static class Request {
        private final String tag;
        private final String authorName;
        private final String FavoriteUserName;
        private final int offset;
        private final int limit;

        public Request(String tag, String authorName, String favoriteUserName, int offset, int limit) {
            this.tag = tag;
            this.authorName = authorName;
            FavoriteUserName = favoriteUserName;
            this.offset = offset;
            this.limit = limit;
        }

        public String getTag() {
            return tag;
        }

        public String getAuthorName() {
            return authorName;
        }

        public String getFavoriteUserName() {
            return FavoriteUserName;
        }

        public int getOffset() {
            return offset;
        }

        public int getLimit() {
            return limit;
        }
    }
}
