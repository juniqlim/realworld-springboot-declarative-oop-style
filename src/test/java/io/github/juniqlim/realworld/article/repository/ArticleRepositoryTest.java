package io.github.juniqlim.realworld.article.repository;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.Id.LongId;
import io.github.juniqlim.realworld.article.domain.Article;
import io.github.juniqlim.realworld.article.domain.Tag;
import io.github.juniqlim.realworld.article.repository.ArticleRepository.Conditional;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class ArticleRepositoryTest {
    @Test
    void save() {
        ArticleRepository articleRepository = new ArticleRepository();
        Article article = new Article("How to train your dragon", "Ever wonder how?",
                "You have to believe", Fixture.LONG_ID_ONE, Arrays.asList(new Tag("reactjs"), new Tag("angularjs"), new Tag("dragons")));

        assertDoesNotThrow(() -> articleRepository.save(article));
    }

    @Test
    void filter() throws InterruptedException {
        ArticleRepository articleRepository = new ArticleRepository();

        for (int i = 1; i < 101; i++) {
            articleRepository.save(new Article(i + "", i + "", i + "", new LongId(i % 2),
                Arrays.asList(new Tag("reactjs" + (i % 3)), new Tag("angularjs" + i), new Tag("dragons" + i))));
            sleep(10);
        }

        List<Article> articles = articleRepository.findByTagAuthorIdFavoriteUserIdOrderByRegdate("reactjs1", new Id.EmptyId(), new Id.EmptyId(), 1, 3);

        System.out.println("articles.size() = " + articles.size());
        articles.forEach(a -> System.out.println("a.getSlug() = " + a.slug() + ", a.getCreateAt = " + a.createdAt()));

        assertEquals(3, articles.size());
        assertEquals("91", articles.get(0).slug());
        assertEquals("88", articles.get(1).slug());
        assertEquals("85", articles.get(2).slug());

        List<Article> articles2 = articleRepository.findByTagAuthorIdFavoriteUserIdOrderByRegdate(null, Fixture.LONG_ID_ONE, new Id.EmptyId(), 0, 100);

        assertEquals(50, articles2.size());
    }

    @Test
    void condition1() {
        Conditional conditional = new Conditional(null, Fixture.LONG_ID_ONE, new Id.EmptyId());
        Article article = new Article("How to train your dragon", "Ever wonder how?",
            "You have to believe", Fixture.LONG_ID_ONE, Arrays.asList(new Tag("reactjs"), new Tag("angularjs"), new Tag("dragons")));

        assertTrue(conditional.value(article));
    }

    @Test
    void condition2() {
        Conditional conditional = new Conditional("re", Fixture.LONG_ID_ONE, new Id.EmptyId());
        Article article = new Article("How to train your dragon", "Ever wonder how?",
            "You have to believe", Fixture.LONG_ID_ONE, Arrays.asList(new Tag("reactjs"), new Tag("angularjs"), new Tag("dragons")));

        assertFalse(conditional.value(article));
    }

    @Test
    void condition3() {
        Conditional conditional = new Conditional(null, new Id.EmptyId(), new Id.EmptyId());
        Article article = new Article("How to train your dragon", "Ever wonder how?",
            "You have to believe", Fixture.LONG_ID_ONE, Arrays.asList(new Tag("reactjs"), new Tag("angularjs"), new Tag("dragons")));

        assertTrue(conditional.value(article));
    }

    @Test
    void condition4() {
        Conditional conditional = new Conditional("reactjs", Fixture.LONG_ID_ONE, new Id.EmptyId());
        Article article = new Article("How to train your dragon", "Ever wonder how?",
            "You have to believe", Fixture.LONG_ID_ONE, Arrays.asList(new Tag("reactjs"), new Tag("angularjs"), new Tag("dragons")));

        assertTrue(conditional.value(article));
    }

    @Test
    void condition5() {
        Conditional conditional = new Conditional("reactjs", Fixture.LONG_ID_ONE, Fixture.LONG_ID_TWO);
        Article article = new Article("How to train your dragon", "Ever wonder how?",
            "You have to believe", Fixture.LONG_ID_ONE, Arrays.asList(new Tag("reactjs"), new Tag("angularjs"), new Tag("dragons")));
        article.favorite(Fixture.LONG_ID_TWO);

        assertTrue(conditional.value(article));
    }

    @Test
    void condition6() {
        Conditional conditional = new Conditional(null, new Id.EmptyId(), new Id.EmptyId());
        Article article = new Article("How to train your dragon", "Ever wonder how?",
            "You have to believe", Fixture.LONG_ID_ONE, Arrays.asList(new Tag("reactjs"), new Tag("angularjs"), new Tag("dragons")));

        assertTrue(conditional.value(article));
    }
}
