package com.cydeo.day10;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;     //restAssured libraray; request, response, everything
import static org.hamcrest.MatcherAssert.assertThat;    //assertThat is coming from here
import static org.hamcrest.Matchers.*;      //is(), equals()

public class GovXmlTest {

    @Test
    public void test1(){
        //send a get request to
        //https://data.ct.gov/api/views/qm34-pq7e/rows.xml
        //get all the years
        //get all unknowns
        //get 2005 other
        //get 2007 _address

        Response response = get("https://data.ct.gov/api/views/qm34-pq7e/rows.xml")
                .then().statusCode(200).extract().response();

        XmlPath xmlPath = response.xmlPath();

        List<Integer> years = xmlPath.getList("response.row.row.year");
        System.out.println("list = " + years);

        List<Integer> unknowns = xmlPath.getList("response.row.row.unknown");
        System.out.println("unknowns = " + unknowns);

        int other2005 = xmlPath.getInt("response.row.row[2].other");
        System.out.println("other2005 = " + other2005);

        String address2007 = xmlPath.getString("response.row.row[4].@_address");
        System.out.println("address = " + address2007);

    }
}
