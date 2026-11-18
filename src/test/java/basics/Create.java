package basics;

import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
//import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Create {

    int id;

    @Test
    public void createUsers() {

        String requestBody = """
                                {
                  "email": "eve.holt@reqres.in",
                  "password": "pistol"
                }
                                """;

        given()
                .contentType("application/json")
                .body(requestBody)

                .when()
                .post("https://reqres.in/api/register")
                // log().all()-> prints the response in console
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void getAttribute() {

        String requestBody = """
                                {
                  "email": "eve.holt@reqres.in",
                  "password": "pistol"
                }
                                """;

        String token =

                given()
                        .contentType("application/json")
                        .body(requestBody)

                        .when()
                        .post("https://reqres.in/api/register")

                        .then()
                        .extract().path("token");
        System.out.println("The token is " + token);

    }

    @Test 
    public void getAttribute1() {

        String requestBody = """
                                {
                  "email": "eve.holt@reqres.in",
                  "password": "pistol"
                }
                                """;

        Response response =

                given()
                        .contentType("application/json")
                        .body(requestBody)

                        .when()
                        .post("https://reqres.in/api/register");

        String token = response.jsonPath().getString("token");
        int id = response.jsonPath().getInt("id");

        System.out.println("The token is " + token);
        System.out.println("The id is " + id);

    }

    @Test
     public void getAttribute2() {

        String requestBody = """
                                {
                  "email": "eve.holt@reqres.in",
                  "password": "pistol"
                }
                                """;

        id =

                given()
                        .contentType("application/json")
                        .body(requestBody)

                        .when()
                        .post("https://reqres.in/api/register").jsonPath().getInt("id");

                        System.out.println("The id " +id);
     }

}
