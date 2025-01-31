package restApi;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;


public class RestApiTest {
//?page=2
    //1:08
    @Test
    public void restApi(){
        RestAssured.baseURI = "https://reqres.in/api";
                         given()
                                 .queryParam("page", 2)
                        .when()
                        .get("/users")
                        .then()
                        .statusCode(200)
                        .log()
                        .all();
    }
}
