

import configuration.Config;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jdk.jfr.Description;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;


import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsInAnyOrder;


public class TaxDutyCalcTests extends Config {

    @Test
    @Description("Verify status 200")
    public void verifyStatus200() {

        given()
                .header(authType, apiKeyAuth)
                .body(itemBodyJson)
                .when()
                .post("shopping-cart")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

    }

    @Test
    @Description("Get Response Time")
    public void captureResponseTime() {
        RequestSpecification request = RestAssured.given();
        request.header(authType, apiKeyAuth);
        request.contentType(ContentType.JSON);
        request.body(itemBodyJson);
        Response response = request.post("/shopping-cart");

        long responseTimeInSeconds = response.getTimeIn(TimeUnit.SECONDS);
        System.out.println("Response time in seconds using getTimeIn():" + responseTimeInSeconds);


    }

    @Test
    @Description("Verify authorization with invalid ApiKeyAuth token returns status 401 ")
    public void verifyStatusUnauthorized() {

        RequestSpecification request = RestAssured.given();
        request.header(authType, invalidApiKeyAuth);
        request.contentType("text/html");
        request.body(itemBodyJson);
        Response response = request.post("/shopping-cart");
        int statusCode = response.statusCode();
        Assert.assertEquals(String.valueOf(statusCode) /*actual value*/, 401 /*expected value*/, "Correct status code returned");

    }


    @Test
    @Description("Test Post Request with key/value check ")
    public void verifyPostRequest() {
        given()

                .contentType(ContentType.JSON)
                .header(authType, apiKeyAuth)
                .body(itemBodyJson)
                .when()
                .post("/shopping-cart")
                .then().log().all()
                .assertThat()
                .body("externalId", containsInAnyOrder("123e4567-e89b-12d3-a456-426655440000"))
                .body("orderCurrency", Matchers.notNullValue());


    }


    @Test
    @Description("Test to retrieve and assert Content-Type")
    public void verifyContentType()
    {
        Response response = RestAssured
                .given()
                .header(authType, apiKeyAuth)
                .contentType(ContentType.JSON)
                .body((itemBodyJson))
                .post("/shopping-cart");
        String contentType = response.getContentType();
        System.out.println("Content-Type of response is : "+contentType);

    }
}









