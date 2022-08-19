package com.cydeo.day2;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class HrGetRequests {

    //BeforeAll is an annotation equal to @BeforeClass in testNG, we use with static method name
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://44.202.93.236:1000/ords/hr";

    }
}
