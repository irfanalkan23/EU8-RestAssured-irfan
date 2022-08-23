package com.cydeo.day4;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanWithJsonPath extends SpartanTestBase {

    /*
Given accept type is json
And path param id is 10
When user sends a get request to "api/spartans/{id}"
Then status code is 200
And content-type is "application/json"
And response payload values match the following:
  id is 10,
  name is "Lorenza",
  gender is "Female",
  phone is 3312820936
*/

    @DisplayName("GET one spartan with JsonPath")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get("api/spartans/{id}");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        //print name with path method
        System.out.println(response.path("name").toString());

        //assignin response to jsonpath
        JsonPath jsonPath = response.jsonPath();

        int id = jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phone = jsonPath.getLong("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

    }
}





