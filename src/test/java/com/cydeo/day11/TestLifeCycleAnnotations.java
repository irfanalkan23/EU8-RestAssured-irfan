package com.cydeo.day11;

import org.junit.jupiter.api.*;

public class TestLifeCycleAnnotations {

    //review of Junit 5 annotations:

    @BeforeAll      // testNG → @BeforeClass
    public static void init(){      // should be static! (happening one time)
        System.out.println("Before all is running");
    }

    @BeforeEach     // TestNG --> @BeforeMethod, Cucumber --> @Before
    public void initEach(){
        System.out.println("\tBefore each is running");
    }

    @AfterEach     // TestNG --> @AfterMethod, Cucumber --> @After
    public void closeEach(){
        System.out.println("\tAfter each is running");
    }

    @Test       //comes from Junit-jupiter
    public void test1(){
        System.out.println("Test 1 is running");
    }

    @Disabled
    @Test
    public void test2(){
        System.out.println("Test 2 is running");
    }

    @AfterAll
    public static void close(){      // should be static! (happening one time)
        System.out.println("After all is running");
    }
}
