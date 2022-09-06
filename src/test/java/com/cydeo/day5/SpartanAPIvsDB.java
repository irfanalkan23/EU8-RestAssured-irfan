package com.cydeo.day5;

import com.cydeo.utilities.DBUtils;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class SpartanAPIvsDB extends SpartanTestBase {
    @DisplayName("GET one spartan from api and database")
    @Test
    public void testDB1(){
        //get id,name,gender phone from database
        //get same information from api
        //compare

        String query = "select spartan_id,name,gender,phone\n" +
                "from spartans\n" +
                "where spartan_id=15";

        //save data inside the map
        Map<String, Object> dbMap = DBUtils.getRowMap(query);
        System.out.println(dbMap);
        //output: {PHONE=1938695106, GENDER=Female, SPARTAN_ID=15, NAME=Meta}

        //--> where is this result coming from? --> DB.
        //there is nothing about API until here!

        //2.get info from api
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", "15")
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .and().contentType("application/json")
                .extract().response();

        //Deserialization here Json to Java with Jackson objectMapper
        Map<String,Object> apiMap = response.as(Map.class);
        System.out.println(apiMap);
        //output: {id=15, name=Meta, gender=Female, phone=1938695106}

        //3.compare database vs api --> we assume expected result is db!
        //the values are the same, but the keys are different!
            //{PHONE=1938695106, GENDER=Female, SPARTAN_ID=15, NAME=Meta}
            //{id=15, name=Meta, gender=Female, phone=1938695106}
        //we can use Junit assertion or Hamcrest assertion
        //
        assertThat(apiMap.get("id").toString(), is(dbMap.get("SPARTAN_ID").toString()));
        assertThat(apiMap.get("name"), is(dbMap.get("NAME")));  //keys are case sensitive
        assertThat(apiMap.get("gender"), is(dbMap.get("GENDER")));
        assertThat(apiMap.get("phone").toString(), is(dbMap.get("PHONE").toString()));


    }
}
