package org.wikipedia.regression;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.wikipedia.common.WikiUtils;

public class DemoAPITest {

    @Test(priority = 1, description = "Verify that the POST API is responding as expected.", groups = {"regression"})
    public void testUserPost() {
        WikiUtils.log("Start of TestCase - testUserPost...");
        SoftAssert softAssert = new SoftAssert();

        // Set Base URI -
        RestAssured.baseURI = "https://reqres.in/api";

        // Creating request body -
        JSONObject request = new JSONObject();
        request.put("name", "Shubham");
        request.put("job", "SDET");
        WikiUtils.log("Request Body => \n" + request.toJSONString());

        Response response = RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(request.toJSONString())
                .when()
                    .post("/users")
                .then()
                    .extract().response();

        String responseBody = response.getBody().asString();
        WikiUtils.log("Response Body: " + responseBody);

        // Assert the values
        softAssert.assertEquals(response.jsonPath().getString("name"), request.get("name"), "Name is incorrect!");
        softAssert.assertEquals(response.jsonPath().getString("job"), request.get("job"), "Job is incorrect!");

        // Assert all
        softAssert.assertAll();
    }
}
