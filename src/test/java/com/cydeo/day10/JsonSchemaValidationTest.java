package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import com.cydeo.utilities.SpartanUtil;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.*;     //restAssured libraray; request, response, everything
import static org.hamcrest.MatcherAssert.assertThat;    //assertThat is coming from here
import static org.hamcrest.Matchers.*;      //is(), equals()

public class JsonSchemaValidationTest extends SpartanAuthTestBase {

    @DisplayName("GET request to verify one spartan against to schema")
    @Test
    public void schemaValidation(){

        given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 20)
                .and()
                .auth().basic("admin", "admin")
        .when()
                .get("/api/spartans/{id}")
        .then()
                .statusCode(200)

                //.body() usage with the new added dependency
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json"))
                //we can just give the file name as the path since it is in the resources directory

                .log().all();
    }

    @DisplayName("GET request to all spartans and verify schema")
    @Test
    public void allSpartanSchemaTest(){

        given()
                .accept(ContentType.JSON)
                .auth().basic("admin", "admin")
        .when()
                .get("/api/spartans")
        .then()
                .statusCode(200)
                //what if you have your .json file not under resources? following way -->
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/com/cydeo/day10/allSpartansSchema.json")));

    }

    //homework
    //put your post json schema under day10
    //post one spartan using dynamic input(name,gender,phone)
    //verify your post response matching with json schema

    @DisplayName("POST request to spartan and verify schema")
    @Test
    public void postSpartanSchemaTest(){
        given()
                .accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .and()
                .contentType(ContentType.JSON)
                .body(SpartanUtil.spartanJsonBodyMap()).log().all()
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201).log().all()
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/com/cydeo/day10/spartanPostJsonSchema.json")));
    }



}
