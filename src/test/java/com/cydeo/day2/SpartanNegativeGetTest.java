package com.cydeo.day2;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static io.restassured.RestAssured.baseURI;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanNegativeGetTest {

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://44.202.93.236:8000";
    }

    /*
    Given Accept type application/xml
    When user send GET request to api/spartans/10 end point
    Then status code must be 406
    Then response Content Type must be application/xml;charset=UTF-8
     */

    @Test
    public void test1(){
        Response response = given().accept(ContentType.XML)
                .when().get("api/spartans/10");
        assertEquals(406, response.statusCode());
        assertEquals("application/xml;charset=UTF-8", response.contentType());
    }


}
