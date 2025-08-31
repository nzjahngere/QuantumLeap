package quantumLeapTests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Login {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @Before
    public void setup() {

        ExtentSparkReporter spark = new ExtentSparkReporter("LoginTestReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);

        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Given("the user is on the login page")
    public void openLoginPage() {
        test = extent.createTest("Execution will begin shortly...!!");
    }

    @When("the user enters username {string} and password {string}")
    public void enterCredentials(String username, String password) {
    	
        WebElement userName = driver.findElement(By.id("user-name"));
        WebElement passWord = driver.findElement(By.id("password"));

        userName.clear();
        userName.sendKeys(username);

        passWord.clear();
        passWord.sendKeys(password);
    }

    @When("clicks the Login button")
    public void clickBtn() {
    	
        driver.findElement(By.id("login-button")).click();
        
    }

    @Then("the system should respond with {string}")
    public void verifyResult(String expRes) {
    	
        if (expRes.equals("redirected to Products page")) {
            String currentUrl = driver.getCurrentUrl();
            Assert.assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html", "User not redirected correctly");
        } else {
            WebElement errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']"));
            String actualText = errorMessage.getText();
            
            if (actualText.startsWith("error ")) {
                actualText = actualText.substring(6);
            }
            
            Assert.assertEquals(actualText, expRes, "Error message mismatch");
        }
        
        test.pass("Execution completed successfully!");
        
    }
    
    @After
    public void closeUp() throws InterruptedException {
    	Thread.sleep(3000);
    	extent.flush();
	    driver.quit();
    }

}
