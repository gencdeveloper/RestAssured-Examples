package day2;

import io.restassured.*;

import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Hamcrest {

    @BeforeEach
    public void init(){
        RestAssured.baseURI = "http://3.91.231.36:8000";
    }

    @Test
    public void EqualsCheckWithAssert(){

        assertThat(5+5 , is(10));
        assertThat(5+5 , equalTo(10));
        assertThat(5+5 , not(11));
        assertThat(5+5 , is(not(11)));
        assertThat(5+5 , is(not(equalTo(12))));


    }

}
