package io.github.juniqlim.realworld.article;

import io.github.juniqlim.realworld.Fixture;
import io.github.juniqlim.realworld.Id;
import io.github.juniqlim.realworld.article.repository.ArticleArrayListRepository;
import io.github.juniqlim.realworld.article.repository.ArticleRepository;
import io.github.juniqlim.realworld.article.web.ArticleResponse;
import io.github.juniqlim.realworld.favorite.FavoriteArticleUseCase;
import io.github.juniqlim.realworld.tag.TagUseCase;
import io.github.juniqlim.realworld.tag.repository.TagRepository;
import io.github.juniqlim.realworld.user.FindProfile;
import io.github.juniqlim.realworld.user.FindUser;
import io.github.juniqlim.realworld.user.FollowUser;
import io.github.juniqlim.realworld.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindArticleResponseTest {
    FindArticleResponse findArticleResponse;
    UserRepository userRepository;

    @BeforeEach
    void setup() {
        ArticleRepository articleRepository = new ArticleArrayListRepository();
        articleRepository.save(Fixture.JAKE_ARTICLE);

        TagUseCase tagUseCase = new TagUseCase(new TagRepository.TagArrayListRepository());
        tagUseCase.merge(new TagUseCase.Request(Fixture.JAKE_ARTICLE.id(), Collections.singletonList("java")));

        userRepository = new UserRepository.UserArrayListRepository();
        userRepository.save(Fixture.JAKE);
        userRepository.save(Fixture.JUNIQ);
        userRepository.save(Fixture.MINK);

        findArticleResponse = new FindArticleResponse(
            new FindArticle(articleRepository), new FindProfile(new FindUser(userRepository), new FollowUser(userRepository)), tagUseCase, new FavoriteArticleUseCase.Fake(), new FollowUser(userRepository)
        );
    }
    @Test
    void find() {
        List<ArticleResponse> articleResponses = findArticleResponse.find(
            new FindArticleResponse.Request.Builder()
                .offset(0)
                .limit(100)
                .tag("java")
                .build()
        );

        assertEquals(Fixture.JAKE_ARTICLE.title(), articleResponses.get(0).getTitle());
    }

    @Test
    void feed() {
        new FollowUser(userRepository).follow(Fixture.JUNIQ.id(), Fixture.JAKE.id());
        new FollowUser(userRepository).follow(Fixture.JUNIQ.id(), Fixture.MINK.id());

        List<ArticleResponse> articles = findArticleResponse.findFeed(
            new FindArticleResponse.Request(
                Fixture.JUNIQ.id(),
                null,
                new Id.EmptyId(),
                new Id.EmptyId(),
                0, 20
            ));

        assertEquals(1, articles.size());
    }
}