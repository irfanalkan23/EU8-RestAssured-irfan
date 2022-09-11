package com.cydeo.day7;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;     //restAssured libraray; request, response, everything
import static org.hamcrest.MatcherAssert.assertThat;    //assertThat is coming from here
import static org.hamcrest.Matchers.*;      //is(), equals()

public class PutAndPatchRequestDemo extends SpartanTestBase {

    @DisplayName("PUT request to one spartan for update with Map")
    @Test
    public void PUTRequest(){
        //just like post request we have different options to send body, we will go with map
        Map<String, Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("name","BruceWayne");
        putRequestMap.put("gender","Male");
        putRequestMap.put("phone",8877441111L);

        given()
                .contentType(ContentType.JSON)
                .body(putRequestMap).log().all()
                .and().pathParam("id",118)
                .when().put("/api/spartans/{id}")
                .then()
                .statusCode(204);

        //send a GET request after update, make sure updated field changed, or the new info matching
        //with requestBody that we send (save in Spartan object)

    }

    @DisplayName("PATCH request to one spartan for partial update with Map")
    @Test
    public void PATCHRequest(){
        //just like post request we have different options to send body, we will go with map
        Map<String, Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("phone",8811111111L);

        given()
                .contentType(ContentType.JSON)
                .body(putRequestMap).log().all()
                .and().pathParam("id",118)
                .when().patch("/api/spartans/{id}")
                .then()
                .statusCode(204);

        //send a GET request after update, make sure updated field changed, or the new info matching
        //with requestBody that we send (save in Spartan object)

    }

    @DisplayName("DELETE one spartan")
    @Test
    public void deleteSpartan(){
        int idToDelete = 117;

        given().pathParam("id", idToDelete)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204);

        //send a get request after you delete make sure you are getting 404

    }
}
