package day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import utility.ApiTestBase;

import java.util.List;
import java.util.Map;

public class JsonToJava extends ApiTestBase {

    // response body to map
    @Test
    public void test1(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("api/spartans/110");

        response.prettyPrint();

        Map<String, Object> responseMap = response.as(Map.class);

        System.out.println(responseMap.toString());

        System.out.println(responseMap.get("id"));
        System.out.println(responseMap.get("name"));

    }

    @Test
    public void test2(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("api/spartans");

        //response.prettyPrint();

        List<Map<String,Object>> responseList = response.as(List.class);

       // System.out.println(responseList);

        //i want no 20 spartan from the list
        System.out.println(responseList.get(20));
        System.out.println(responseList.get(19).get("id"));
        System.out.println(responseList.get(19).get("name"));
        System.out.println(responseList.get(19).get("phone"));

    }
}
