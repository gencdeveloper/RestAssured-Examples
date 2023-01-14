package utility;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;

public class ApiTestBase {

    @BeforeEach
    public void init(){
        RestAssured.baseURI = "http://3.91.231.36:8000";
    }


}
