package io.github.juniqlim.realworld.integrationtest.user;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class CreateUser {
    ExtractableResponse<Response> createUser(Object body) {
        return given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(body)
            .when()
            .post("/api/users")
            .then()
            .log().all().extract();
    }

    public String createUserGetToken(Object body) {
        return createUser(body).jsonPath().getString("user.token");
    }
}
