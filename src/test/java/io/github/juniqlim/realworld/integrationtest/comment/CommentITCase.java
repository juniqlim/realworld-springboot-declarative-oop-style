package io.github.juniqlim.realworld.integrationtest.comment;

import io.github.juniqlim.realworld.integrationtest.HttpApiConfig;
import io.github.juniqlim.realworld.integrationtest.ITFixture;
import io.github.juniqlim.realworld.integrationtest.article.CreateArticle;
import io.github.juniqlim.realworld.integrationtest.user.CreateUser;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommentITCase extends HttpApiConfig {
    @Test
    void comment() {
        String token = new CreateUser().createUserGetToken(ITFixture.JAKE);
        ExtractableResponse<Response> articleResponse = new CreateArticle().create(token, ITFixture.JAKE_ARTICLE);

        ExtractableResponse<Response> commentResponse = RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header("Authorization", "Token " + token)
            .body(ITFixture.JAKE_COMMENT)
            .when()
            .post(
                "/api/articles/{slug}/comments",
                articleResponse.jsonPath().getString("article.slug")
            )
            .then()
            .log().all().extract();
        assertEquals("It's easy.", commentResponse.jsonPath().getString("comment.body"));
    }
}
