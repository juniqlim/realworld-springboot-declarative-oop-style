package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.article.FindArticle.Request;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.repository.ArticleArrayListRepository;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindArticleTest {
    ArticleRepository articleRepository = new ArticleArrayListRepository();

    @BeforeEach
    void setUp() {
        articleRepository.save(Fixture.JAKE_ARTICLE);
    }

    @Test
    void test() {
        assertEquals("How to train your dragon",
            new FindArticle(articleRepository).find("how-to-train-your-dragon").title());
    }

    @Test
    void findByAuthorName() {
        assertEquals("How to train your dragon",
            new FindArticle(articleRepository).find(new FindArticle.Request(new ArrayList<>(), Fixture.JAKE.id(), new Id.EmptyId(), 0, 100))
                .get(0).title());
    }

    @Test
    void findByFavoriteUserName() {
        Article favoritedArticle = articleRepository.findBySlug("how-to-train-your-dragon").favorite(Fixture.JAKE.id());
        articleRepository.update(favoritedArticle.slug(), favoritedArticle);

        assertEquals("How to train your dragon",
            new FindArticle(articleRepository).find(
                    new Request(new ArrayList<>(), new Id.EmptyId(), Fixture.JAKE.id(), 0, 100))
                .get(0).title());
    }
}
