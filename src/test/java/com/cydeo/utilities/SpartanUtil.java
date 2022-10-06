package com.cydeo.utilities;

import com.cydeo.pojo.Spartan;
import com.github.javafaker.Faker;
import com.github.javafaker.PhoneNumber;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class SpartanUtil {

    public static Map<String,Object> spartanJsonBodyMap(){
        Map<String,Object> requestJsonMap = new LinkedHashMap<>();
        Faker faker = new Faker();

        requestJsonMap.put("name", faker.name().firstName());

//        if (faker.random().nextBoolean().equals(true)){
//            requestJsonMap.put("gender", "Male");
//        } else {
//            requestJsonMap.put("gender", "Female");
//        }

        requestJsonMap.put("gender", faker.demographic().sex());
        requestJsonMap.put("phone", faker.numerify("##########"));

        return requestJsonMap;
    }
}
