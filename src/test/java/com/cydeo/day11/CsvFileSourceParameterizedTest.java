package com.cydeo.day11;

import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.*;     //restAssured libraray; request, response, everything
import static org.hamcrest.MatcherAssert.assertThat;    //assertThat is coming from here
import static org.hamcrest.Matchers.*;      //is(), equals()

public class CsvFileSourceParameterizedTest {

    // Write a parameterized test for this request
    // Get the data from csv source
    // GET https://api.zippopotam.us/us/{state}/{city}
    @ParameterizedTest
    @CsvFileSource(resources = "/postalCode.csv", numLinesToSkip = 1)
    //numLinesToSkip=1 --> means "skip the column name row"
    public void postalCodeTestWithFile(String stateArg, String cityArg, int postalCodeArg) {
        System.out.println("stateArg = " + stateArg);
        System.out.println("cityArg = " + cityArg);
        System.out.println("postalCodeArg = " + postalCodeArg);
        //send a request and verify place number matches with zipCount
        given()
                .accept(ContentType.JSON)
                .baseUri("https://api.zippopotam.us")
                .pathParam("state",stateArg)
                .pathParam("city",cityArg)
                .log().uri()
        .when()
                .get("/us/{state}/{city}")
        .then()
                .statusCode(200)
                .body("places",hasSize(postalCodeArg));

    }
}
