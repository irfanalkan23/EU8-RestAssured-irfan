package com.cydeo.utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

//we don't need to create objects from this class, so we can make it abstract
public abstract class HRTestBase {

    //BeforeAll is an annotation equal to @BeforeClass in testNG, we use with static method name
    @BeforeAll
    public static void init() {
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://44.202.93.236:1000/ords/hr";

        //Task: get ip address from configurations
    }
}
