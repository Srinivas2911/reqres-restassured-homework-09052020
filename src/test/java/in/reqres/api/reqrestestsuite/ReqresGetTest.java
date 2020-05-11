package in.reqres.api.reqrestestsuite;

/*
Created by SP
*/

import in.reqres.api.testbase.TestBase;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ReqresGetTest extends TestBase {

    @Test
    public void getAllUserInfo() {
        given()
                .queryParam("page", "2")
                .when()
                .get("/users")
                .then().statusCode(200)
                .log().body()
                .body("data.size()", equalTo(6));
    }

    @Test
    public void searchUserWithID() {
        given()
                //.log().params()
                .when()
                .get("/users/2")
                .then().statusCode(200).log().body()
                .body("data.id", equalTo(2),
                        "data.email", equalTo("janet.weaver@reqres.in"),
                        "data.first_name", equalTo("Janet"),
                        "data.last_name", equalTo("Weaver"),
                        "data.avatar", equalTo("https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg"));
    }

    @Test
    public void singleUserNotFound() {
        given()
                .when()
                .get("/users/23")
                .then().statusCode(404)
                .log().status();
    }

    @Test
    public void getListResourceInfo() {
        given()
                .when()
                .get("/unknown")
                .then().statusCode(200)
                .log().body()
                .body("data.size()", equalTo(6));
    }
}

