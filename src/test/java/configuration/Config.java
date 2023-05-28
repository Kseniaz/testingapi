package configuration;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.BeforeClass;


import static io.restassured.http.ContentType.JSON;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.Matchers.lessThanOrEqualTo;


public class Config {


    @BeforeClass
    public static void setupSpec() {
        RestAssured.baseURI = "https://api.integration.eurora.com/";
        RestAssured.basePath = "customs-calculator/v1/";
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType("application/json")
                .addHeader("Accept", "application/json")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .log(LogDetail.STATUS)
                .expectContentType(JSON)
                .expectStatusCode(200)
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)
                .build();

    }

    public String authType = "X-Auth-Token";
    public String apiKeyAuth = "f4afd290-d92f-4113-a232-47959157137e.wRf46QemaVeS2s1wEzF2CC691gtrUUZN";
    public String invalidApiKeyAuth = "f4afd290-d92f-4113-a232-47959157137e";

    public String invalidApi = "shop";
    public String itemBodyJson = "[\n" +
            "  {\n" +
            "    \"externalId\": \"123e4567-e89b-12d3-a456-426655440000\",\n" +
            "    \"orderCurrency\": \"EUR\",\n" +
            "    \"transportationPrice\": 5000.55,\n" +
            "    \"insurancePrice\": 100,\n" +
            "    \"otherAdditionalCosts\": 15.55,\n" +
            "    \"originCountry\": \"CN\",\n" +
            "    \"destinationCountry\": \"CA\",\n" +
            "    \"destinationRegion\": \"CA-MB\",\n" +
            "    \"additionalValueShare\": \"MANUAL\",\n" +
            "    \"goods\": [\n" +
            "      {\n" +
            "        \"externalId\": \"123e4567-e89b-12d3-a456-426655440000\",\n" +
            "        \"originCountry\": \"CN\",\n" +
            "        \"gtin\": \"00012345600012\",\n" +
            "        \"title\": \"Fidget spinners\",\n" +
            "        \"description\": \"Fidget spinners\",\n" +
            "        \"hsCode\": \"0101000000\",\n" +
            "        \"price\": {\n" +
            "          \"currency\": \"EUR\",\n" +
            "          \"value\": 11.25\n" +
            "        },\n" +
            "        \"weight\": 0.15,\n" +
            "        \"quantity\": 250,\n" +
            "        \"additionalValueShareRatio\": 1\n" +
            "      }\n" +
            "    ],\n" +
            "    \"date\": \"2023-05-24\",\n" +
            "    \"useDeMinimis\": true,\n" +
            "    \"hsCodeReplaceAllowed\": true,\n" +
            "    \"declarationType\": \"H7\",\n" +
            "    \"transactionModel\": \"B2B\",\n" +
            "    \"transactionCategory\": \"COMMERCIAL\"\n" +
            "  }\n" +
            "]";
}