package io.github.juniqlim.realworld.article.repository.rdb;

import io.github.juniqlim.realworld.Fixture;

import java.time.LocalDateTime;

class RDBFixture {
    public static final ArticleEntity JAKE_ARTICLE_ENTITY = new ArticleEntity(Fixture.LONG_ID_ONE.value(), "how-to-train-your-dragon", "How to train your dragon",
        "Ever wonder how?", "You have to believe", LocalDateTime.now(), LocalDateTime.now(), 1, Fixture.JAKE.id().value());
    public static final ArticleEntity JUNIQ_ARTICLE_ENTITY = new ArticleEntity(Fixture.LONG_ID_TWO.value(), "good-day", "Good day", "So toothless",
        "You have to believe", LocalDateTime.now(), LocalDateTime.now(), 0, Fixture.JUNIQ.id().value());
}
