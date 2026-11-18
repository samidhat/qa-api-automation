package basics;

import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

/* 
// given()
// content type, set cookies, add auth, add param, set headers info etc..

// when()
// get, post, put, delete

// then()
// validate status code, extract response, extraxt header cookies and response body..

*/

public class Get {

    @Test
    public void getUsers() {

        given()
                .when()
                .get("https://reqres.in/api/users/2")
                // log().all()-> prints the response in console
                .then().statusCode(200).body("data.id", equalTo(2))
                .body("data.email", containsString("@reqres.in"))
                .log().all();
    }

    
}
