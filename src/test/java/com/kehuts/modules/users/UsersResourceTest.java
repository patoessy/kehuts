package com.kehuts.modules.users;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class UsersResourceTest {

    @Test
    public void testHelloEndpoint() {
        var user = new UserInputModel(
                "Patrick Musembi",
                254_702_867_620,
                "Juja",
                "patpat@gmail.com");

        given()
                .body(user)
          .when().post("/rest/create-user")
          .then()
             .statusCode(200)
             .body(is("created"));
    }

}