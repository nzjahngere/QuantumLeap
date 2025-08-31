package apiTests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;

public class mainTests extends baseTests {
	
	WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeMethod
    public void setup() {
    	
    	ExtentSparkReporter spark = new ExtentSparkReporter("APITestsReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        
    }

	// RA-001: GET Users
    @Test(priority = 1)
    public void getUsersTest() {
        String response =
                given()
                    .spec(requestSpec)
                    .log().all()
                .when()
                    .get("/api/users?page=2")
                .then()
                    .log().all()
                    .statusCode(200)
                    .extract().asString();

        JsonPath js = new JsonPath(response);
        Assert.assertTrue(js.getList("data").size() > 0, "User list should not be empty");
        Assert.assertNotNull(js.get("data[0].id"), "First user's ID should not be null");
        Assert.assertNotNull(js.get("data[0].email"), "First user's email should not be null");
    }

    // POST: Create User
    
    @Test(priority = 1)
    public void createUserTest() {
        String requestBody = "{ \"name\": \"morpheus\", \"job\": \"QA Engineer\" }";

        String response =
                given()
                    .spec(requestSpec)
                    .log().all()
                    .body(requestBody)
                .when()
                    .post("/api/users")
                .then()
                    .log().all()
                    .statusCode(201)
                    .extract().asString();

        JsonPath js = new JsonPath(response);
        Assert.assertEquals(js.get("name"), "morpheus");
        Assert.assertEquals(js.get("job"), "QA Engineer");
        Assert.assertNotNull(js.get("id"), "User ID should be generated");
    }

    // PUT: Update User
    
    @Test(priority = 2)
    public void updateUserTest() {
        String requestBody = "{ \"name\": \"neo\", \"job\": \"SDET\" }";

        String response =
                given()
                    .spec(requestSpec)
                    .log().all()
                    .body(requestBody)
                .when()
                    .put("/api/users/2")
                .then()
                    .log().all()
                    .statusCode(200)
                    .extract().asString();

        JsonPath js = new JsonPath(response);
        Assert.assertEquals(js.get("name"), "neo");
        Assert.assertEquals(js.get("job"), "SDET");
    }

    // DELETE: Delete User
    
    @Test(priority = 3)
    public void deleteUserTest() {
        given()
            .spec(requestSpec)
            .log().all()
        .when()
            .delete("/api/users/2")
        .then()
            .log().all()
            .statusCode(204);
    }
    
    @AfterMethod
    public void tearDown() throws InterruptedException {
    	
        extent.flush();
        
    }

}
