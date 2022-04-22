package com.kehuts.module.org.apartment.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kehuts.module.org.apartment.model.Apartment;
import com.kehuts.module.org.apartment.model.dto.ApartmentDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class ApartmentResourceTest {
    //test fetching all apartments
    //test apartment edit

    @Test
    @Order(1)
    void shouldAddApartmentSuccessfully() throws JsonProcessingException {
        ObjectMapper ob = new ObjectMapper();
        ApartmentDTO apartment = new ApartmentDTO(
                "Hellena Plaza",
                "Juja",
                null,
                null,
                null,
                null,
                null
        );

        given().when()
                .contentType(ContentType.JSON)
                .body(ob.writeValueAsString(apartment))
                .post("/rest/apartment")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON);
    }

    @Test
    @Order(2)
    void fetchAllApartments(){
        given().when()
                .get("/rest/apartment")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }
}
