package in.reqres.api.reqrestestsuite;

/*
Created by SP
*/

import in.reqres.api.testbase.TestBase;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ReqresDelTest extends TestBase {

    @Test
    public void deleteAUserInfo() {
        given()
                .when()
                .delete("/users/2")
                .then().statusCode(204)
                .log().status()
                .log().ifValidationFails();
    }

}
