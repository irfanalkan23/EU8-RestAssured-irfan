package com.cydeo.day7;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import com.cydeo.utilities.SpartanUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;     //restAssured libraray; request, response, everything
import static org.hamcrest.MatcherAssert.assertThat;    //assertThat is coming from here
import static org.hamcrest.Matchers.*;      //is(), equals()

public class SpartanPostRequestDemo extends SpartanTestBase {

           /*
    Given accept type and Content type is JSON
    And request json body is:
    {
      "gender":"Male",
      "name":"Severus",
      "phone":8877445596
   }
    When user sends POST request to '/api/spartans'
    Then status code 201
    And content type should be application/json
    And json payload/response/body should contain:
    "A Spartan is Born!" message
    and same data what is posted
 */

    @Test
    public void postMethod1(){
        String requestJsonBody = "{\"gender\":\"Male\",\n" +
                "\"name\":\"Severus\",\n" +
                "\"phone\":8877445596}";

        Response response = given().accept(ContentType.JSON).and()  //I want to accept JSON
                .contentType(ContentType.JSON)  //I'm sending JSON
                .body(requestJsonBody)     //I'm sending it in the body part in my request
                .when()
                .post("/api/spartans");// this is dictated in the API doc in POST (swagger)

        //we can do the assertions in 6 different ways
        //let's do it in the old way, not in chaining
        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"), is(expectedResponseMessage));
        assertThat(response.path("data.name"), is("Severus"));
        assertThat(response.path("data.gender"), is("Male"));
        assertThat(response.path("data.phone"), is(8877445596L));

    }

    @DisplayName("POST with Map to JSON")
    @Test
    public void postMethod2(){

        //create a map to keep "request json body" information
//        Map<String,Object> requestJsonMap = new LinkedHashMap<>();
//        requestJsonMap.put("name", "Severus");
//        requestJsonMap.put("gender", "Male");
//        requestJsonMap.put("phone", 8877445596L);
        Map<String, Object> requestJsonMap = SpartanUtil.spartanJsonBodyMap();
        System.out.println("requestJsonMap = " + requestJsonMap);

        Response response = given().accept(ContentType.JSON).and()  //"I want to accept JSON"
                .contentType(ContentType.JSON)  //"I'm sending JSON"
                .body(requestJsonMap).log().all()     //Jackson (or Gson) makes auto-serialization!
                .when()
                .post("/api/spartans");// this is dictated in the API doc in POST (swagger)

        //we can do the assertions in 6 different ways
        //let's do it in the old way, not in chaining
        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"), is(expectedResponseMessage));
        assertThat(response.path("data.name"), is(requestJsonMap.get("name")));
        assertThat(response.path("data.gender"), is(requestJsonMap.get("gender")));
        assertThat(response.path("data.phone"), is(requestJsonMap.get("phone")));

        //let's see the response part as well
        response.prettyPrint();

    }

    @DisplayName("POST with Map to Spartan Class")
    @Test
    public void postMethod3(){

        //create one object from your pojo, send it as a JSON
        Spartan spartan = new Spartan();
        spartan.setName("Severus");
        spartan.setGender("Male");
        spartan.setPhone(8877445596L);

        System.out.println("spartan = " + spartan);

        Response response = given().accept(ContentType.JSON).and()  //"I want to accept JSON"
                .contentType(ContentType.JSON)  //"I'm sending JSON"
                .body(spartan).log().all()     //Jackson (or Gson) makes auto-serialization!
                .when()
                .post("/api/spartans");// this is dictated in the API doc in POST (swagger)

        //we can do the assertions in 6 different ways
        //let's do it in the old way, not in chaining
        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"), is(expectedResponseMessage));
        assertThat(response.path("data.name"), is("Severus"));
        assertThat(response.path("data.gender"), is("Male"));
        assertThat(response.path("data.phone"), is(8877445596L));

        //let's see the response part as well
        response.prettyPrint();

    }

    @DisplayName("POST with Map to Spartan Class")
    @Test
    public void postMethod4(){
        //this example we implement serialization with creating spartan object sending as a request body
        //also implemented deserialization getting the id,sending get request and saving that body as a response

        //create one object from your pojo, send it as a JSON
        //the purpose is to create the request body
        Spartan spartan = new Spartan();
        spartan.setName("Severus");
        spartan.setGender("Male");
        spartan.setPhone(8877445596L);

        System.out.println("spartan = " + spartan);
        String expectedResponseMessage = "A Spartan is Born!";

        int idFromPost = given().accept(ContentType.JSON).and()  //"I want to accept JSON"
                .contentType(ContentType.JSON)  //"I'm sending JSON"
                .body(spartan).log().all()     //Jackson (or Gson) makes auto-serialization!
                .when()
                .post("/api/spartans") // this is dictated in the API doc in POST (swagger)
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("success", is(expectedResponseMessage))
                .extract().jsonPath().getInt("data.id");

        System.out.println("idFromPost = " + idFromPost);
        //send a get request to id
        Spartan spartanPosted = given().accept(ContentType.JSON)
                .and().pathParam("id", idFromPost)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).log().all().extract().as(Spartan.class);

        assertThat(spartanPosted.getName(), is(spartan.getName()));
        assertThat(spartanPosted.getGender(), is(spartan.getGender()));
        assertThat(spartanPosted.getPhone(), is(spartan.getPhone()));
        assertThat(spartanPosted.getId(), is(idFromPost));

    }

    //Create one SpartanUtil class
    //create a static method that returns Map<String,Object>
    //use faker library(add as a dependency) to assign each time different information
    //for name,gender,phone number
    //then use your method for creating spartan as a map,dynamically.
    //--> task completed --> postMethod2()

}
