package com.cydeo.day12;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;     //restAssured libraray; request, response, everything
import static org.hamcrest.MatcherAssert.assertThat;    //assertThat is coming from here
import static org.hamcrest.Matchers.*;      //is(), equals()

public class MockApi {

//    https://a4286128-410f-471b-8918-5c9add282abe.mock.pstmn.io

    @Test
    public void test1(){
        given().baseUri("https://a4286128-410f-471b-8918-5c9add282abe.mock.pstmn.io")
                .accept(ContentType.JSON)
        .when()
                .get("/customer")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstName",is("John"));
    }

    @Test
    public void test2(){
        given().baseUri("https://a4286128-410f-471b-8918-5c9add282abe.mock.pstmn.io")
                .accept(ContentType.JSON)
                .when()
                .get("/employees")
                .prettyPrint();
    }

}
