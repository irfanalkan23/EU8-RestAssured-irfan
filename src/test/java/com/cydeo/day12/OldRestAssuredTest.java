package com.cydeo.day12;

import com.cydeo.utilities.SpartanNewBase;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;     //restAssured libraray; request, response, everything
import static org.hamcrest.MatcherAssert.assertThat;    //assertThat is coming from here
import static org.hamcrest.Matchers.*;      //is(), equals()

public class OldRestAssuredTest extends SpartanNewBase {

    @Test
    public void getAllSpartan(){
        given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin","admin")
                .log().all()
        .when()
                .get("/spartans")
                // "/api" part is in the SpartanNewBase util in basePath
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id[0]", is(10))
                //if the first assertion fails, the test will stop = "hard assertion"
                .body("id[5]", is(199))
                .log().all();
    }

    // now, let's see the OLD syntax:
    @Test
    public void test2(){
        /*
            in previous version of Restassured, the "given when then" style
            was actually written in "given expect when" format.
            it will assert all the result and give one answer and does not fail whole thing
            if first one fail unlike new structure.
        */
        given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin","admin")
                .log().all()
        //old way, instead of then() -->
        .expect()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)  // *--> same as .contentType("application/json")
                .body("id[0]", is(10))
                //even if the first assertion fails, the test will continue = "soft assertion"
                .body("id[5]", is(199))
                .logDetail(LogDetail.ALL)   //log way using with expect()
        .when()
                .get("/spartans");
    }

}
