package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.article.repository.ArticleArrayListRepository;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.article.web.ArticleResponse;
import io.github.juniqlim.realworld.favorite.FavoriteArticleUseCase;
import io.github.juniqlim.realworld.tag.TagUseCase;
import io.github.juniqlim.realworld.tag.repository.TagRepository;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindArticleResponseTest {
    @Test
    void test() {
        ArticleRepository articleRepository = new ArticleArrayListRepository();
        articleRepository.save(Fixture.JAKE_ARTICLE);
        TagUseCase tagUseCase = new TagUseCase(new TagRepository.TagArrayListRepository());
        tagUseCase.merge(new TagUseCase.Request(Fixture.JAKE_ARTICLE.id(), Collections.singletonList("java")));
        UserRepository.Collection userRepository = new UserRepository.Collection();
        userRepository.save(Fixture.JAKE);

        FindArticleResponse findArticleResponse = new FindArticleResponse(
            new FindArticle(articleRepository), tagUseCase, new FindUser(userRepository), new FavoriteArticleUseCase.Fake()
        );

        List<ArticleResponse> articleResponses = findArticleResponse.find(
            new FindArticleResponse.Request.Builder()
                .offset(0)
                .limit(100)
                .tag("java")
                .build()
        );

        assertEquals(Fixture.JAKE_ARTICLE.title(), articleResponses.get(0).getTitle());
    }
}