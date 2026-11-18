package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints1;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {

    Faker faker;
    User userPayload;
    public Logger logger;

    @BeforeTest
    public void setData() {
        faker = new Faker();
        userPayload = new User();

        userPayload.setUserName(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.lorem().characters(6));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
        userPayload.setId(faker.idNumber().hashCode());

        System.out.println("The username set is " + userPayload.getUserName());

        // logs
        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void testPostUser() {
        logger.debug("Creating user...");

        Response response = UserEndPoints1.createUser(userPayload);

        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println("The username in post is " + userPayload.getUserName());
    }

    @Test(priority = 2)
    public void testReadUser() {
        logger.info("Reading user info...");
        System.out.println("The username in read is " + userPayload.getUserName());
        Response response = UserEndPoints.readUser(userPayload.getUserName());

        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    // @Test(priority = 3)
    public void testUpdateUser() {
        logger.info("Updating user info...");
        // Update the payload
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndPoints.updateUser(this.userPayload.getUserName(), userPayload);

        response.then().log().body().statusCode(200);
        Assert.assertEquals(response.getStatusCode(), 200);

        // Check the update userpayload
        Response responseAfterUpdate = UserEndPoints.readUser(this.userPayload.getUserName());
        responseAfterUpdate.then().log().all();
    }

    @Test(priority = 4)
    public void testDeleteUser() {
        logger.info("Deleting user...");
        Response response = UserEndPoints.deleteUser(this.userPayload.getUserName());

        response.then().statusCode(200);
    }

}
