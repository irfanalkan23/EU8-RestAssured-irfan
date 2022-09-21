package com.cydeo.day11;

import com.cydeo.utilities.ExcelUtil;
import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static io.restassured.RestAssured.*;     //restAssured libraray; request, response, everything
import static org.hamcrest.MatcherAssert.assertThat;    //assertThat is coming from here
import static org.hamcrest.Matchers.*;      //is(), equals()

import java.util.List;
import java.util.Map;

public class BookitParameterized {

    public static List<Map<String,String>> getExcelData(){
        ExcelUtil bookitFile = new ExcelUtil("src/test/resources/BookItQa3.xlsx", "QA3");
        return bookitFile.getDataList();
    }

    @ParameterizedTest
    @MethodSource("getExcelData")
    public void bookItTest(Map<String,String> user){
        System.out.println("user.get(\"email\") = " + user.get("email"));
        System.out.println("user.get(\"password\") = " + user.get("password"));

        given()
                .accept(ContentType.JSON)
                .baseUri("https://cybertek-reservation-api-qa3.herokuapp.com")
                .queryParams(user)  //I pass map directly because query param keys and map keys are equal
                .when()
                .get("/sign")
                .then()
                .statusCode(200)
                .log().all();

    }

}
