package in.reqres.api.reqrestestsuite;

/*
Created by SP
*/

import in.reqres.api.ReqResPojo;
import in.reqres.api.testbase.TestBase;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ReqresPatchTest extends TestBase {

    String job = null;

    @Test
    public void patchUserWithJob() {
        ReqResPojo reqResPojo = new ReqResPojo();
        reqResPojo.setJob(job ="Cinema" + getRandomString(3));

        given()
                .header("Content-Type", "application/json")
                .when().log().body()
                .body(reqResPojo)
                .patch("/5")
                .then()
                .statusCode(200).log().body()
                .body("job", equalTo(job));

    }
}
