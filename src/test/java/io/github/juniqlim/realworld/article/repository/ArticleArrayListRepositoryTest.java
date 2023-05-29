package io.github.juniqlim.realworld.article.repository;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.article.domain.Article;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ArticleArrayListRepositoryTest {
    @Test
    void save() {
        ArticleRepository articleRepository = new ArticleArrayListRepository();
        assertDoesNotThrow(() -> articleRepository.save(Fixture.JAKE_ARTICLE));
    }

    @Test
    void filter() throws InterruptedException {
        ArticleRepository articleRepository = new ArticleArrayListRepository();

        for (int i = 1; i < 101; i++) {
            articleRepository.save(new Article(new Id.LongId(i), i + "", i + "", i + "", new Id.LongId(i % 2 + 1)));
            sleep(10);
        }

        List<Article> articles = articleRepository.findByRequest(new ArticleRepository.Conditions(Arrays.asList(new Id.LongId(91), new Id.LongId(88), new Id.LongId(85)), new Id.EmptyId(), 0, 20));

        System.out.println("articles.size() = " + articles.size());
        articles.forEach(a -> System.out.println("a.getSlug() = " + a.slug() + ", a.getCreateAt = " + a.createdAt()));

        assertEquals(3, articles.size());
        assertEquals("91", articles.get(0).slug());
        assertEquals("88", articles.get(1).slug());
        assertEquals("85", articles.get(2).slug());

        List<Article> articles2 = articleRepository.findByRequest(new ArticleRepository.Conditions(new ArrayList<>(), Fixture.LONG_ID_ONE,0, 100));

        assertEquals(50, articles2.size());
    }
}
