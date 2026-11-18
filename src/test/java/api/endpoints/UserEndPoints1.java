package api.endpoints;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

// This class required to perform CRUD operations

public class UserEndPoints1 {

    public static ResourceBundle getUrl(){
        
        ResourceBundle resource = ResourceBundle.getBundle("route"); //load properties file
        return resource;
    }

    public static Response createUser(User payload) {

        String post_url = getUrl().getString("post_url");
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)

                .when()
                .post(post_url);

        return response;

    }

    public static Response readUser(String username) {

        String get_url = getUrl().getString("get_url");
        Response response = given().pathParam("username", username)

                .when()
                .get(get_url);
        return response;

    }

    public static Response updateUser(String username, User payload) {
 
        String update_url = getUrl().getString("update_url");
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("UserName", username)
                .body(payload)

                .when()
                .put(update_url);

        return response;

    }

      public static Response deleteUser(String username) {

        String delete_url = getUrl().getString("delete_url");
        Response response = given().pathParam("username", username)

                .when()
                .delete(delete_url);
        return response;

    }

}
