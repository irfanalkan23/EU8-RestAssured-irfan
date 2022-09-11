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
        String fakerName = faker.name().firstName();
        Boolean fakerGender = faker.random().nextBoolean();
        Long fakerPhone = faker.number().numberBetween(1000000000L,9999999999L);

        requestJsonMap.put("name", fakerName);

        if (fakerGender.equals(true)){
            requestJsonMap.put("gender", "Male");
        } else {
            requestJsonMap.put("gender", "Female");
        }

        requestJsonMap.put("phone", fakerPhone);

        return requestJsonMap;
    }
}
