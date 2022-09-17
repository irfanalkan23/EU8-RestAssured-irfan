package com.cydeo.utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class SpartanAuthTestBase {
    @BeforeAll
    public static void init() {
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://44.195.19.167:7000";  //I used Jamal's IP 52.207.61.129   //44.195.19.167:7000 ?

    }
}