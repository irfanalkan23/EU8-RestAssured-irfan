package com.cydeo.day5;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ORDSHamcrestTest extends HRTestBase {

    @DisplayName("GET request to Employees IT_PROG endpoint and chaining")
    @Test
    public void employeesTest(){
        //send a get request to employees endpoint with query paramaeter job_id IT_PROG
        //verify each job_id is IT_PROG
        //verify first names are .... (find proper method to check list against list)
        //verify emails without checking order (provide emails in different order, just make sure it has same emails)

        //expected names
        List<String> names = Arrays.asList("Alexander","Bruce","David","Valli","Diana");

        given()
                .accept(ContentType.JSON)
                //see page 28 for queryParams in ORDS
                .queryParams("q", "{\"job_id\": \"IT_PROG\"}")
        .when()
                .get("/employees")
        .then()
                .statusCode(200)
                .body("items.job_id", everyItem(equalTo("IT_PROG")))
                .body("items.first_name", containsInRelativeOrder("Alexander","Bruce","David","Valli","Diana")) //contains with order
                .body("items.email", containsInAnyOrder("VPATABAL","DAUSTIN","BERNST","AHUNOLD","DLORENTZ")) //contains without order
                .body("items.first_name", equalToObject(names)); // equality of lists assertion

    }

    @Test
    public void employeesTest2(){
        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                //see page 28 for queryParams in ORDS
                .queryParams("q", "{\"job_id\": \"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(equalTo("IT_PROG")))
                .extract().jsonPath();

        //extract() --> method that allow us to get response object after we use then() method.
        //assert that we have only 5 firstnames
        assertThat(jsonPath.getList("items.first_name"),hasSize(5));

        //assert firstnames order
        assertThat(jsonPath.getList("items.first_name"),containsInRelativeOrder("Alexander","Bruce","David","Valli","Diana"));

    }
}
