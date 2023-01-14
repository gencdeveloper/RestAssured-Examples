package day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ApiTestBase;

public class JsonPath extends ApiTestBase {

    /**JSON PATJ*/
    //we are using json path method, for getting information from our response body
    //json path allows you to select and extract data from a JSON document.
    //it has a lot of functions, but we need to know below ones:
        //get
        //getString
        //getInt
        //getLong
        //getObject

    /*****************************/
    /**POJO
     * Plain Old Java Object
     * Convert response body to java objects that we are familiar, which is Map,List or Custom Class
     * The purpose is to get the data easily
     * How to do that?
     *  Map<String, Object> responseMap = response.as(Map.class);-->MAP
     *  List<Map<String, Object>> responseList = response.as(List.class);--->List
     *  Spartan newSpartan = response.as(Spartan.class); --->custom class
     * */



    @DisplayName("Using JsonPath")
    @Test
    public void test1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("/api/spartans/110");

        response.prettyPrint();

        io.restassured.path.json.JsonPath jsonPath = response.jsonPath();

        String name = jsonPath.getString("name");
        Long phone = jsonPath.getLong("phone");
        String gender = jsonPath.getString("gender");

        System.out.println(name);
        System.out.println(phone);
        System.out.println(gender);

    }
}
