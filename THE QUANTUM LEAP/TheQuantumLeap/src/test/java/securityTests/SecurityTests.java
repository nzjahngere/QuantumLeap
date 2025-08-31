package securityTests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class SecurityTests {
	
    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeMethod
    public void setup() {
    	
    	ExtentSparkReporter spark = new ExtentSparkReporter("SecurityTestingReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);

        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
    }

    @Test
    public void testSQLInjectionOnLogin() {
    	
        driver.findElement(By.id("user-name")).sendKeys("' OR '1'='1");
        driver.findElement(By.id("password")).sendKeys("' OR '1'='1");
        driver.findElement(By.id("login-button")).click();

        WebElement errMsg = driver.findElement(By.cssSelector("h3[data-test='error']"));
        Assert.assertTrue(errMsg.isDisplayed(), "SQL Injection attempt should fail but it passed!");
        
    }

    @Test
    public void testXSSInjectionLogin() {

        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.id("login-button"));

        username.sendKeys("<script>alert(1)</script>");
        password.sendKeys("dummyPassword");
        loginBtn.click();

        WebElement errorMsg = driver.findElement(By.cssSelector("h3[data-test='error']"));
        Assert.assertTrue(errorMsg.isDisplayed(), "XSS Injection attempt executed instead of failing!");
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
    	
    	Thread.sleep(2000);
        extent.flush();
        if (driver != null) {
            driver.quit();
        }
        
    }
    
}

