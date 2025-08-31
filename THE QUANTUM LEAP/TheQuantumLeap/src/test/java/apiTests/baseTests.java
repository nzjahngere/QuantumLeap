package apiTests;

import org.testng.annotations.BeforeClass;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class baseTests {

    protected static RequestSpecification requestSpec;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://reqres.in";
        requestSpec = new RequestSpecBuilder()
                .addHeader("x-api-key", "reqres-free-v1")
                .setContentType("application/json")
                .build();
        System.out.println("Base URI is set to: " + RestAssured.baseURI);
        System.out.println("API Key is set for requests.");
    }
}
