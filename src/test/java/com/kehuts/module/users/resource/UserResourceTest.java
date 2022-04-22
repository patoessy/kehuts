package com.kehuts.module.users.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kehuts.module.users.model.dto.UserDto;
import com.kehuts.module.users.model.dto.UserInputModel;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static  org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@QuarkusTest
class UserResourceTest {

    @TestTransaction
    String registerUser(UserInputModel userInputModel) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return given().when()
                .body(objectMapper.writeValueAsString(userInputModel))
                .contentType(ContentType.JSON)
                .post("/rest/user/register-tenant")
                .then()
                .statusCode(201)
                .extract()
                .asString();
    }

    @Test
    @Order(1)
    void Given_TENANTREGISTRATIONDETAILS_When_REGISTERING_THEN_REGISTERSUCCESSFULLY()
            throws JsonProcessingException {
        UserInputModel userInputModel = new UserInputModel(
                "Patrick Musembi",
                254758240927L,
                "patumusembi7@gmail.com",
                "1234"
        );
        String registered = registerUser(userInputModel);
        assertThat(registered.equals("User created successfully"), is(true));
    }

    @Test
    @Order(2)
    void Given_UserIsLoggedIn_When_LogingIn_Then_LoginThemSuccessfully() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        UserInputModel userInputModel = new UserInputModel(
                "Patrick Musembi",
                254759240927L,
                "patumusembi70@gmail.com",
                "1234"
        );

        registerUser(userInputModel);

        String userString = given().when()
                .body(objectMapper.writeValueAsString(userInputModel))
                .contentType(ContentType.JSON)
                .post("/rest/user/login")
                .then()
                .statusCode(200)
                .extract()
                .asString();
        UserDto userDto = objectMapper.readValue(userString, UserDto.class);
        assertNotNull(userDto);
        assertNotNull(userDto.token());
    }
}