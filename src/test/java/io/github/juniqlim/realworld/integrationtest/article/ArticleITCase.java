package io.github.juniqlim.realworld.integrationtest.article;

import io.github.juniqlim.realworld.Fixture;
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
        String token = new CreateUser().createUserGetToken(ITFixture.JAKE);
        ExtractableResponse<Response> response = new CreateArticle().create(token, ITFixture.JAKE_ARTICLE);

        assertEquals("How to train your dragon", response.jsonPath().getString("article.title"));

        ExtractableResponse<Response> findResponse = RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header("Authorization", "Token " + token)
            .body(ITFixture.JAKE_ARTICLE)
            .when()
            .get("/api/articles/{slug}", "how-to-train-your-dragon")
            .then()
            .log().all().extract();
        assertEquals("How to train your dragon", findResponse.jsonPath().getString("article.title"));
    }

    @Test
    void update() {
        String token = new CreateUser().createUserGetToken(ITFixture.JAKE);
        ExtractableResponse<Response> response = new CreateArticle().create(token, ITFixture.JAKE_ARTICLE);

        ExtractableResponse<Response> updatedResponse = RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header("Authorization", "Token " + token)
            .body(
                "{\n" +
                "  \"article\": {\n" +
                "    \"title\": \"Did you train your dragon?\",\n" +
                "    \"description\": \"2Did you train your dragon?\",\n" +
                "    \"body\": \"3Did you train your dragon?\"\n" +
                "  }\n" +
                "}"
            )
            .when()
            .put("/api/articles/{slug}", response.jsonPath().getString("article.slug"))
            .then()
            .log().all().extract();
        assertEquals("Did you train your dragon?", updatedResponse.jsonPath().getString("article.title"));
        assertEquals("2Did you train your dragon?", updatedResponse.jsonPath().getString("article.description"));
        assertEquals("3Did you train your dragon?", updatedResponse.jsonPath().getString("article.body"));
    }

    @Test
    void findArticles() {
        String token = new CreateUser().createUserGetToken(ITFixture.JAKE);

        new CreateArticle().create(token, ITFixture.JAKE_ARTICLE);
        new CreateArticle().create(new CreateUser().createUserGetToken(ITFixture.JUNIQ), ITFixture.JUNIQ_ARTICLE);

        ExtractableResponse<Response> findResponse = RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header("Authorization", "Token " + token)
            .when()
            .get("/api/articles?tag=reactjs&offset=1&limit=20")
            .then()
            .log().all().extract();
        assertEquals("How to train your dragon", findResponse.jsonPath().getString("articles[0].title"));
    }

    @Test
    void feedArticles() {
        String token = new CreateUser().createUserGetToken(ITFixture.JAKE);

        new CreateArticle().create(token, ITFixture.JAKE_ARTICLE);
        new CreateArticle().create(new CreateUser().createUserGetToken(ITFixture.JUNIQ), ITFixture.JUNIQ_ARTICLE);

        RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header("Authorization", "Token " + token)
            .when()
            .post("/api/profiles/" + Fixture.JUNIQ.username() + "/follow")
            .then()
            .log().all().extract();

        ExtractableResponse<Response> findResponse = RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header("Authorization", "Token " + token)
            .when()
            .get("/api/articles/feed?offset=1&limit=20")
            .then()
            .log().all().extract();
        assertEquals("Good day", findResponse.jsonPath().getString("articles[0].title"));
    }
}
