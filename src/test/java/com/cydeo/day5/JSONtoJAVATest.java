package com.cydeo.day5;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JSONtoJAVATest extends SpartanTestBase {

    @DisplayName("GET one Spartan and deserialize to Map")
    @Test
    public void oneSpartanToMap(){
        Response response = given().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).extract().response();

        //get the json and convert it to the map
        Map<String,Object> jsonMap = response.as(Map.class);

        //when we run up to here, we get this fail message:
        // java.lang.IllegalStateException: Cannot parse object because no JSON deserializer found in classpath.
        // Please put either Jackson (Databind) or Gson in the classpath.
    }

}
