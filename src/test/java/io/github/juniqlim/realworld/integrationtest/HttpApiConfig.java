package io.github.juniqlim.realworld.integrationtest;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public
class HttpApiConfig {
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        int port = RestAssured.port;
        if(port == RestAssured.UNDEFINED_PORT) {
            RestAssured.port = this.port;
        }
    }
}
