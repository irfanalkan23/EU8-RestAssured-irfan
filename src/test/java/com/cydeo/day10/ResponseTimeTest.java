package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;     //restAssured libraray; request, response, everything
import static org.hamcrest.MatcherAssert.assertThat;    //assertThat is coming from here
import static org.hamcrest.Matchers.*;      //is(), equals()

public class ResponseTimeTest extends SpartanAuthTestBase {
    @Test
    public void test1(){
        Response response = given()
                .auth().basic("admin", "admin")
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans")
                .then()

                //time() method uses Hamcrest Matchers
//                .time(lessThanOrEqualTo(1200L))
                .time(both(greaterThan(500L)).and(lessThanOrEqualTo(1200L)))
                .extract().response();

        System.out.println("response.getTime() = " + response.getTime());

    }

}
