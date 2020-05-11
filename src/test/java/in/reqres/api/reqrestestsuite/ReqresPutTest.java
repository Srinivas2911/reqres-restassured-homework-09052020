package in.reqres.api.reqrestestsuite;

/*
Created by SP
*/

import in.reqres.api.ReqResPojo;
import in.reqres.api.testbase.TestBase;
import org.junit.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ReqresPutTest extends TestBase {

    String name = null;
    String job = null;

    @Test
    public void updateUserWithID() {
    ReqResPojo reqResPojo = new ReqResPojo();
        reqResPojo.setName(name = "Johny" + getRandomString(3));
        reqResPojo.setJob(job ="Golf" + getRandomString(3));

        given()
                .header("Content-Type", "application/json")
                .when().log().body()
                .body(reqResPojo)
                .put("/5")
                .then()
                .statusCode(200).log().body()
                .body("name", equalTo(name),
                        "job", equalTo(job));

    }

}
