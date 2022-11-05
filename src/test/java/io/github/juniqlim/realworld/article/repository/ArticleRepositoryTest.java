package io.github.juniqlim.realworld.article.repository;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.juniqlim.realworld.article.domain.Article;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class ArticleRepositoryTest {
    @Test
    void save() {
        ArticleRepository articleRepository = new ArticleRepository();
        Article article = new Article("How to train your dragon", "Ever wonder how?",
                "You have to believe", "idid", Arrays.asList("reactjs", "angularjs", "dragons"));

        assertDoesNotThrow(() -> articleRepository.save(article));
    }

    @Test
    void filter() throws InterruptedException {
        ArticleRepository articleRepository = new ArticleRepository();

        for (int i = 1; i < 101; i++) {
            articleRepository.save(new Article(i + "", i + "", i + "", "idid" + (i % 2),
                    Arrays.asList("reactjs" + (i % 3), "angularjs" + i, "dragons" + i)));
            sleep(10);
        }

        List<Article> articles = articleRepository.findByTagAuthorIdOrderByRegdate("reactjs1", null, 1, 3);

        System.out.println("articles.size() = " + articles.size());
        articles.forEach(a -> System.out.println("a.getSlug() = " + a.getSlug() + ", a.getCreateAt = " + a.getCreatAt()));

        assertEquals(3, articles.size());
        assertEquals("91", articles.get(0).getSlug());
        assertEquals("88", articles.get(1).getSlug());
        assertEquals("85", articles.get(2).getSlug());

        List<Article> articles2 = articleRepository.findByTagAuthorIdOrderByRegdate(null, "idid1", 0, 100);

        assertEquals(50, articles2.size());
    }
}
