package com.cydeo.day6;

import com.cydeo.pojo.Region;
import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ORDSPojoGetRequestTest extends HRTestBase {

    //create pojo classes for this response (--> json object of "region_id": 1)
    //{
    //            "region_id": 1,
    //            "region_name": "Europe",
    //            "links": [
    //                {
    //                    "rel": "self",
    //                    "href": "http://44.202.93.236:1000/ords/hr/regions/1"
    //                }
    //            ]
    //        },
    //then test it with sending get request to regions endpoint and
    //only pointing first region and converting your pojos

    @Test
    public void regionTest(){

        JsonPath jsonPath = get("/regions").then().statusCode(200).extract().jsonPath();

        Region region1 = jsonPath.getObject("items[0]", Region.class);

        System.out.println(region1);

        System.out.println("region1.getRegion_id() = " + region1.getRegion_id());
        System.out.println("region1.getRegion_name() = " + region1.getRegion_name());
        System.out.println("region1.getLinks().get(0).getHref() = " + region1.getLinks().get(0).getHref());

    }
}
