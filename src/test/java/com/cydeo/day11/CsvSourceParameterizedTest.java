package com.cydeo.day11;

import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static io.restassured.RestAssured.*;     //restAssured libraray; request, response, everything
import static org.hamcrest.MatcherAssert.assertThat;    //assertThat is coming from here
import static org.hamcrest.Matchers.*;      //is(), equals()

public class CsvSourceParameterizedTest {

// Test first number + second number = third number
//            1, 3 , 4
//            2, 3 , 5
//            8, 7 , 15
//            10, 9 , 19

    @ParameterizedTest
    @CsvSource({"1,3,4",
            "2,3,5",
            "8,7,15",
            "10,9,19"})
    public void testAddition(int num1, int num2, int sum){
        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
        System.out.println("sum = " + sum);
        //assert num1+num2=sum
        assertThat(num1+num2, equalTo(sum));    //comes from RestAssured
                    //Hamcrest is also independent library, but it comes with Rest Assured library
                    //we can use JUnit5 assertion too:
//        Assertions.assertEquals((num1 + num2), sum);
                    // Hamcrest is easier. It has; everyItem, hasItem, hasItems, hasSize
    }

    // Write a parameterized test for this request
    // GET https://api.zippopotam.us/us/{state}/{city}
    /*
        "NY, New York",
        "CO, Denver",
        "VA, Fairfax",
        "VA, Arlington",
        "MA, Boston",
        "NY, New York",
        "MD, Annapolis"
     */
    //verify place name contains your city name
    //print number of places for each request

    @ParameterizedTest
    @CsvSource({"NY, New York",
            "CO, Denver",
            "VA, Fairfax",
            "VA, Arlington",
            "MA, Boston",
            "NY, New York",
            "MD, Annapolis"})
    public void zipCodeMultiInputTest(String state, String city){

        System.out.println("state = " + state);
        System.out.println("city = " + city);

        int placeNumber = given()
                .baseUri("https://api.zippopotam.us")
                .accept(ContentType.JSON)
                .pathParam("state", state)
                .pathParam("city", city)
//                .pathParams("state", state, "city",city)  //this also works
                .log().uri()
                .when()
                .get("/us/{state}/{city}")
                .then()
                .statusCode(200)
                .body("places.'place name'", everyItem(containsStringIgnoringCase(city)))
                //difference bw hasItem() and everyItem() ?
                //--> hasItem(); passes the test even if only one item matches
                //--> everyItem(); iterates through each item
//                .log().body()
                .extract()
                .jsonPath().getList("places").size();

        System.out.println("placeNumber = " + placeNumber);
    }

}
