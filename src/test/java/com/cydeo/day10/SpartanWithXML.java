package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;     //restAssured libraray; request, response, everything
import static org.hamcrest.MatcherAssert.assertThat;    //assertThat is coming from here
import static org.hamcrest.Matchers.*;      //is(), equals()

public class SpartanWithXML extends SpartanAuthTestBase {

    @DisplayName("GET request to /api/spartans and verify xml")
    @Test
    public void getSpartanXml(){

        //we will ask for xml response
        //assert status code 200
        //assert content  type is xml (we got xml response)
        //verify first spartan name is Meade
        given()
                .accept(ContentType.XML)    //we want xml response
                .and()
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .contentType("application/xml;charset=UTF-8")   //verify we got xml through header
                .body("List.item[0].name",is("MarkoBiniBrock"))
                .body("List.item[0].gender",is("Male"))
                .log().all();
    }

    @DisplayName("GET request /api/spartans with xmlPath")
    @Test
    public void testXmlPath(){
        Response response = given()
                .accept(ContentType.XML)    //we want xml response
                .and()
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans");

        XmlPath xmlPath = response.xmlPath();   //instead of jsonPath()

        //get first spartan name
        String name = xmlPath.getString("List.item[0].name");
        System.out.println("name = " + name);

        //get the 3rd spartan id number
        int id = xmlPath.getInt("List.item[2].id");
        System.out.println("id = " + id);

        //how to get all names and save into list of string
        List<Object> names = xmlPath.getList("List.item.name");
        System.out.println(names);


    }
}
