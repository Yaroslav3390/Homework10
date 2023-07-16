package org.example;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 4, 5})

    public void parametrsTestGetPositive(int id) {

        given()
                .when()
                .log()
                .all()
                .get("http://51.250.6.164:8080/test-orders/{id}", id)
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK);
    }
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5})

    public void parametrsTestGetNegative(int id) {


          given()
                .when()
                .log()
                .all()
                .get("http://51.250.6.164:8080/test-orders/{id}", id)
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }


    @Test

    public void TestPostPositive() {
        String requestBody = "{\n" +
                "  \"status\": \"OPEN\",\n" +
                "  \"courierId\": 0,\n" +
                "  \"customerName\": \"string\",\n" +
                "  \"customerPhone\": \"string\",\n" +
                "  \"comment\": \"string\",\n" +
                "  \"id\": -1340938102087703600\n" +
                "}";


        given()
                .when()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .log()
                .all()
                .post("http://51.250.6.164:8080/test-orders")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test

    public void TestPostNegative() {
        String requestBody = "{\n" +
                "  \"status\": \"OPEN\",\n" +
                "  \"courierId\": 0,\n" +
                "  \"customerName\": \"string\",\n" +
                "  \"customerPhone\": \"string\",\n" +
                "  \"comment\": \"string\",\n" +
                "  \"id\": -1340938102087703600\n" +
                "}";


        given()
                .when()
                .header("Content-Type", "application/xml")
                .body(requestBody)
                .log()
                .all()
                .post("http://51.250.6.164:8080/test-orders")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
    }








    @Test

    public void testGetPositiveOrderStatus() {

       String status = given()
                .when()
                .log()
                .all()
                .get("http://51.250.6.164:8080/test-orders/5")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .path("status");
       Assertions.assertEquals("OPEN", status);

    }
}
