package io.github.juniqlim.realworld.integrationtest.article;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class CreateArticle {
    public ExtractableResponse<Response> create(String token, String body) {
        return RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header("Authorization", "Token " + token)
            .body(body)
            .when()
            .post("/api/articles")
            .then()
            .log().all().extract();
    }
}
