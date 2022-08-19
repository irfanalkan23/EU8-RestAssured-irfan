package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HrGetRequests {

    //BeforeAll is an annotation equal to @BeforeClass in testNG, we use with static method name
    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http method.
        RestAssured.baseURI = "http://44.202.93.236:1000/ords/hr";

    }

    @DisplayName("GET request to /regions")
    @Test
    public void test1(){
        //since we have @BeforeAll baseURI, we only type endpoint
        Response response = RestAssured.get("/regions");

        //print the status code
        System.out.println(response.statusCode());
    }

         /*
        Given accept type is application/json
        When user sends get request to /regions/2
        Then response status code must be 200
        and content type equals to application/json
        and response body contains   Americas
     */

    @DisplayName("GET request to /regions/2")
    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("/regions/2");

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("application/json", response.contentType());

        response.prettyPrint();
        Assertions.assertTrue(response.body().asString().contains("Americas"));

    }
}
