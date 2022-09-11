package com.cydeo.pojo;
/*
{
    "id": 15,
    "name": "Meta",
    "gender": "Female",
    "phone": 1938695106
}
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

@JsonIgnoreProperties(value = "id", allowSetters = true)
//telling Jackson;
// use "id" when deserializing (--> GET),
// DO NOT use "id" when serializing! (while converting to JSON) (--> POST)

public class Spartan {
    //getter setter
    //toString

    private int id;
    private String name;
    private String gender;
    private long phone;


}
