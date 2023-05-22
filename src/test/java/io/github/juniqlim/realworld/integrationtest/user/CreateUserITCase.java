package io.github.juniqlim.realworld.integrationtest.user;

import io.github.juniqlim.realworld.integrationtest.HttpApiConfig;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateUserITCase extends HttpApiConfig {
    @Test
    void test() {
        ExtractableResponse<Response> response = new CreateUser().createUser(
            "{\n" +
                "  \"user\": {\n" +
                "    \"username\": \"Jacob\",\n" +
                "    \"email\": \"jake@jake.jake\",\n" +
                "    \"password\": \"jakejake\"\n" +
                "  }\n" +
                "}");

        assertEquals("Jacob", response.jsonPath().getString("user.username"));
    }
}
