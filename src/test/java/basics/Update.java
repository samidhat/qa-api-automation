package basics;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

public class Update {

    String id;

    // @Test
    // public void createUser() {
    // String requestBody = """
    // {
    // "email": "eve.holt@reqres.in",
    // "password": "pistol"
    // }
    // """;

    // id =

    // given()
    // .contentType("application/json")
    // .body(requestBody)

    // .when()
    // .post("https://reqres.in/api/register").jsonPath().getInt("id");

    // System.out.println("The id " + id);
    // }

    @Test
    public void createUser() {
        String requestBody = """
                                              {
                                "name": "morpheus",
                "job": "consultant"
                              }
                                              """;

        id = given()
                .contentType("application/json")
                .body(requestBody)

                .when()
                .post("https://reqres.in/api/users")

                .then()
                .statusCode(201).extract().path("id");
        System.out.println("The id fetched is " + id);

    }

    @Test(dependsOnMethods = "createUser")
    public void updateUser() {

        String requestBody = """
                                              {
                                "name": "morpheus test",
                "job": "test update"
                              }
                                              """;

        given()
                .contentType("application/json")
                .body(requestBody)

                .when()
                .put("https://reqres.in/api/users/" + id)

                .then().log().all();

    }

    @Test(dependsOnMethods = "updateUser")
    public void deleteUser() {

        given()

                .when()
                .delete("https://reqres.in/api/users/" + id)

                .then().statusCode(204).log().all();
    }

}
