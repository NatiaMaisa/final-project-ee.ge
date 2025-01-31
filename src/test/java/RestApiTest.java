import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RestApiTest {
    @Test
    public void restApi(){
        RestAssured.baseURI = "https://reqres.in";

        given()
                .queryParam("page", 2)
                .when()
                .get("/api/users")
                .then()
                .statusCode(200)
                .time(lessThan(2000L))
                .log().all();
    }
}
