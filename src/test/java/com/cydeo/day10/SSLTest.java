package com.cydeo.day10;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;     //restAssured libraray; request, response, everything
import static org.hamcrest.MatcherAssert.assertThat;    //assertThat is coming from here
import static org.hamcrest.Matchers.*;      //is(), equals()

public class SSLTest {

    @Test
    public void test1(){
        given()
                .relaxedHTTPSValidation()   //even if it doesnt have valid certificate I want to send request
                .when().get("https://untrusted-root.badssl.com/")
                .prettyPrint();
    }

    @Test
    public void keystroke(){
        //this is just to give the methods. not to run.
        given()
                .keyStore("pathtofile","password")
                .when().get("apiurl");
    }
}
