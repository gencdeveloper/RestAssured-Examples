package day2;

import POJO.Search;
import POJO.Spartan;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import utility.ApiTestBase;

public class SpartanDataToClass extends ApiTestBase {

    @Test
    public void test1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .get("/api/spartans/110");

        response.prettyPrint();

       Spartan spartan110 = response.as(Spartan.class);

       System.out.println(spartan110.toString());
    }

    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .get("/api/spartans");

        JsonPath jsonPath = response.jsonPath();

        //System.out.println(jsonPath.getList(""));

        Spartan spartan1 = jsonPath.getObject("[1]",Spartan.class);

       Spartan spartan2 = jsonPath.getObject("[2]",Spartan.class);

        System.out.println(spartan1.toString());
       System.out.println(spartan2.toString());


    }

    @Test
    public void test3(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().queryParam("gender","Female")
                .when().get("/api/spartans/search");

        //response.prettyPrint();

       Search female = response.as(Search.class);
        System.out.println(female);
    }




}
