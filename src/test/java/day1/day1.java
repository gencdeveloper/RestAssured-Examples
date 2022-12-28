package day1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class day1 {


    @BeforeEach
            public void init(){
      RestAssured.baseURI = "http://3.91.231.36:8000";
    }

    String url = "http://3.91.231.36:8000/api/spartans";

    String BaseUrl = "http://3.91.231.36:8000";

    @Test
    public void test1(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .get(url);

        // response.prettyPrint(); --> list of spartans
        System.out.println(response.statusCode());
        Assertions.assertEquals(200,response.statusCode());


    }

    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .get(url);

        System.out.println(response.getHeaders().toString());

        Assertions.assertEquals("application/json",response.contentType());
        Assertions.assertEquals("keep-alive",response.getHeader("Connection"));

        Assertions.assertEquals("chunked",response.getHeader("Transfer-Encoding"));



    }

    @Test
    public void test3(){
        Response response = RestAssured.given().accept(ContentType.XML)
                .get(BaseUrl + "/api/spartans");
        response.prettyPrint();

    }


//get body information
    @Test
    public void test4(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .get("/api/spartans/108");
        response.prettyPrint();
        System.out.println(response.path("name").toString());
        System.out.println(response.path("gender").toString());
        System.out.println(response.path("phone").toString());

    }

    @Test
    public void test5(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().pathParam("id",108)
                .when().get("/api/spartans/{id}");
        response.prettyPrint();

    }

    @Test
    public void test6(){
        Response responseGender = RestAssured.given().accept(ContentType.JSON)
                .when().queryParam("gender","female")
                .when().get("/api/spartans/search");
        responseGender.prettyPrint();


        Response responseName= RestAssured.given().accept(ContentType.JSON)
                .when().queryParam("nameContains","An")
                .when().get("/api/spartans/search");
        responseName.prettyPrint();

        Response responseMix = RestAssured.given().accept(ContentType.JSON)
                .when().queryParam("gender","female")
                .when().queryParam("nameContains","An")
                .when().get("/api/spartans/search");
        responseMix.prettyPrint();

    }

    //query params  as a map object
    @Test
    public void test7(){
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("gender","Female");
        queryParams.put("nameContains","An");

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().queryParams(queryParams)
                .when().get("/api/spartans/search");
        response.prettyPrint();
    }


    //megative test
    @Test
    public void test8(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("/api/spartans/100000");

        Assertions.assertEquals(404,response.statusCode());

    }

    //Post new data
    @Test
    public void test9() {

        String newSpartan = "{\n" +
                "   \"gender\": \"Male\",\n" +
                "   \"name\": \"Emre\",\n" +
                "   \"phone\": 12345678921\n" +

                "}";


        Response response = RestAssured.given().accept(ContentType.JSON)
                    .and()
                    .contentType(ContentType.JSON)
                    .body(newSpartan)
                    .when().post("/api/spartans");

        System.out.println(response.statusCode());
        response.prettyPrint();
        Assertions.assertEquals("A Spartan is Born!",response.path("success"));

    }





    //update spartan
    @Test
    public void test10() {

        String updateSpartan = "{\n" +
                "   \"gender\": \"Female\",\n" +
                "   \"name\": \"Ruveyda\",\n" +
                "   \"phone\": 1243424921\n" +

                "}";


        Response response = RestAssured.given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(updateSpartan)
                .pathParam("id",2)
                .when().put("/api/spartans/{id}");

        System.out.println(response.statusCode());
        response.prettyPrint();


    }

    // delete a spartan
    @Test
    public void test11(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 313)
                .when().delete("/api/spartans/{id}");

        System.out.println(response.statusCode());
    }



}
