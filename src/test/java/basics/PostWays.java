package basics;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class PostWays {

    String id;

    @Test
    public void hashmapWay() {

        Map<String, Object> hm = new HashMap<>();
        hm.put("firstName", "Abu");
        hm.put("lastName", "Koroma");
        hm.put("email", "Abu@test.com");
        hm.put("designation", "Technology head");
        hm.put("salary", 700000);
        hm.put("city", "Pune");

        Response response = given()
                .contentType("application/json")
                .body(hm)

                .when()
                .post("http://localhost:3000/employees")

                .then().statusCode(201)
                .body("firstName", equalTo("Abu"))
                .body("lastName", equalTo("Koroma"))
                .body("salary", equalTo(700000))
                .extract().response();

        id = response.jsonPath().getString("id");
        System.out.println("The id is " + id);
    }

    @Test
    public void deleteUser() {

        given()

                .when()
                .delete("http://localhost:3000/employees/a4vOzp679Dk")

                .then().statusCode(200).log().all();
    }

    @Test
    public void orgJsonWay() {

        JSONObject jb = new JSONObject();

        jb.put("firstName", "Abu");
        jb.put("lastName", "Koroma");
        jb.put("email", "Abu@test.com");
        jb.put("designation", "Technology head");
        jb.put("salary", 700000);
        jb.put("city", "Pune");

        String coursesArr[] = { "Java", "Python" };
        jb.put("courses", coursesArr);

        given()
                .log().all()
                .contentType("application/json")
                .body(jb.toString())

                .when()
                .post("http://localhost:3000/employees")

                .then().statusCode(201)
                .body("firstName", equalTo("Abu"))
                .body("lastName", equalTo("Koroma"))
                .body("salary", equalTo(700000))
                .log().all();

    }

    @Test
    public void pojoWays() {
        Pojo_PostWays pp = new Pojo_PostWays();

        pp.setName("Samidha");
        pp.setEmail("samidha@test.com");
        pp.setSalary(2000000);

        String courseArr[] = { "Selenium", "Playwright" };
        pp.setCourses(courseArr);

        given()
                .contentType("application/json")
                .body(pp)

                .when()
                .post("http://localhost:3000/employees")

                .then().statusCode(201)
                .body("name", equalTo("Samidha"))
                .body("email", equalTo("samidha@test.com"))
                .body("salary", equalTo(2000000))
                .body("courses[0]", equalTo("Selenium"))
                .log().all();
    }

    @Test
    void jsonImportWay() throws FileNotFoundException {

        File fl = new File(".\\body.json");
        FileReader fr = new FileReader(fl);
        JSONTokener jt = new JSONTokener(fr);

        JSONObject data = new JSONObject(jt);

        given()
                .log().all()
                .contentType("application/json")
                .body(data.toString())

                .when()
                .post("http://localhost:3000/employees")

                .then().statusCode(201)
                .body("firstName", equalTo("Samidha"))
                .body("lastName", equalTo("Tonde"))
                .body("salary", equalTo(120000))
                .log().all();

    }
}
