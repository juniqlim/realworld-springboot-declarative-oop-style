package io.github.juniqlim.realworld.integrationtest.article;

import io.github.juniqlim.realworld.integrationtest.HttpApiConfig;
import io.github.juniqlim.realworld.integrationtest.ITFixture;
import io.github.juniqlim.realworld.integrationtest.user.CreateUser;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArticleITCase extends HttpApiConfig {
    @Test
    void create() {
        String token = new CreateUser().createUserGetToken(ITFixture.JAKE2);
        ExtractableResponse<Response> response = new CreateArticle().create(token, ITFixture.JAKE2_ARTICLE);

        assertEquals("How to train your dragon2", response.jsonPath().getString("article.title"));

        ExtractableResponse<Response> findResponse = RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header("Authorization", "Token " + token)
            .body(ITFixture.JAKE2_ARTICLE)
            .when()
            .get("/api/articles/{slug}", "how-to-train-your-dragon2")
            .then()
            .log().all().extract();
        assertEquals("How to train your dragon2", findResponse.jsonPath().getString("article.title"));
    }
}
