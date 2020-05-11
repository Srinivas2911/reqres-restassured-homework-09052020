package in.reqres.api.reqrestestsuite;

/*
Created by SP
*/

import in.reqres.api.ReqResPojo;
import in.reqres.api.testbase.TestBase;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ReqresPostTest extends TestBase {

    String name = "Roger" + getRandomString(3);
    String job = "Acting" + getRandomString(3);
    String email = "Roger" + getRandomString(3) + "@gmail.com";
    String password = getRandomString(8);

    @Test
    public void createNewUser() {

        ReqResPojo reqResPojo = new ReqResPojo();
        reqResPojo.setName(name);
        reqResPojo.setJob(job);

        given()
                .header("Content-Type", "application/json")
                .when()
                .log().body()
                .body(reqResPojo)
                .post("/users")
                .then().statusCode(201)
                .log().body()
                .body("name", equalTo(name),
                        "job", equalTo(job));
        //    response.prettyPrint();
    }

    @Test
    public void userRegisterSuccessfully() {

        ReqResPojo reqResPojo = new ReqResPojo();
        reqResPojo.setEmail(email = "eve.holt@reqres.in");
        reqResPojo.setPassword(password);

        given()
                .header("Content-Type", "application/json")
                .when()
                .log().body()
                .body(reqResPojo)
                .post("/register")
                .then().statusCode(200)
                .log().status()
                .log().body();
        //    response.prettyPrint();
    }

    @Test
    public void userRegistrationUnSuccessful() {

        ReqResPojo reqResPojo = new ReqResPojo();
        reqResPojo.setEmail(email);
        reqResPojo.setPassword("");

        given()
                .header("Content-Type", "application/json")
                .when()
                .log().body()
                .body(reqResPojo)
                .post("/register")
                .then().statusCode(400)
                .log().status()
                .log().body()
                .body("error", equalTo("Missing password"));
    }

    @Test
    public void userLoginSuccessfully() {

        ReqResPojo reqResPojo = new ReqResPojo();
        reqResPojo.setEmail(email = "eve.holt@reqres.in");
        reqResPojo.setPassword(password = "cityslicka");

        given()
                .header("Content-Type", "application/json")
                .when()
                .log().body()
                .body(reqResPojo)
                .post("/login")
                .then().statusCode(200)
                .log().status()
                .log().body();
        //    response.prettyPrint();
    }

    @Test
    public void userLoginUnSuccessful() {

        ReqResPojo reqResPojo = new ReqResPojo();
        reqResPojo.setEmail(email);
        reqResPojo.setPassword(password);

        given()
                .header("Content-Type", "application/json")
                .when()
                .log().body()
                .body(reqResPojo)
                .post("/login")
                .then().statusCode(400)
                .log().status()
                .log().body()
                .body("error", equalTo("user not found"));
    }


}
