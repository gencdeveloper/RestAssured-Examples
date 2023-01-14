package day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import utility.ApiTestBase;

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
}
