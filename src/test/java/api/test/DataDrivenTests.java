package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostUser(String userID, String userName, String fname, String lname, String email, String pwd,
            String phone) {

                System.out.println("ID=" + userID +
            ", Username=" + userName +
            ", Email=" + email);

        User userPayload = new User();
        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUserName(userName);
        userPayload.setFirstName(fname);
        userPayload.setLastName(lname);
        userPayload.setEmail(email);
        userPayload.setPassword(pwd);
        userPayload.setPhone(phone);

        Response response = UserEndPoints.createUser(userPayload);

        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println("The username in post is " + userPayload.getUserName());

    }

    @Test(priority = 1, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testDeleteUser(String userName) {

        Response response = UserEndPoints.deleteUser(userName);

        response.then().statusCode(200);
    }

}
