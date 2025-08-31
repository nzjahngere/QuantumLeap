package quantumLeapTests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Checkout {
	
	WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @Before
    public void setup() {
    	
        ExtentSparkReporter spark = new ExtentSparkReporter("CheckoutReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);

        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    
    @Given("user is on the login page")
    public void entercredentials() {
    	
        test = extent.createTest("Execution will begin shortly...!!");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        
    }
    
    @When("user enters credentials and click submit")
    public void clickLogin() {
    	
        driver.findElement(By.id("login-button")).click();
        
    }
    
    @Then("user is redirected to the Products page")
    public void valRes() {
    	
        test.info("Login successful!");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
        test.pass("Landed Home!");
        
    }
    
    @When("user adds all products to the cart")
    public void allProds() throws InterruptedException {
    	
        List<WebElement> addButtons = driver.findElements(By.xpath("//button[text()='Add to cart']"));
        for (WebElement btn : addButtons) {
            btn.click();
            Thread.sleep(1000);
        }
        
        test.info("All products added to cart");
        
    }

    @When("user navigates to the cart page")
    public void navtocart() {
    	
        driver.findElement(By.className("shopping_cart_link")).click();
        test.info("Navigated to cart page");
        
    }

    @When("user clicks on checkout button")
    public void clickchk() {
    	
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
        checkoutBtn.click();
        
        test.info("Clicked on checkout button");
        
    }


    @When("user enters {string} {string} {string} as checkout information")
    public void chkinfo(String first, String last, String zip) {
    	
        driver.findElement(By.id("first-name")).sendKeys(first);
        driver.findElement(By.id("last-name")).sendKeys(last);
        driver.findElement(By.id("postal-code")).sendKeys(zip);
        
    }

    @When("user clicks continue button")
    public void cntnuBtn() {
    	
        driver.findElement(By.id("continue")).click();
        
    }

    @Then("user should see order overview page")
    public void ordOv() {
    	
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-two.html"));
        test.pass("User reached Order Overview Page");
        
    }

    @Then("user clicks finish button")
    public void clickFin() throws InterruptedException {
    	
        driver.findElement(By.id("finish")).click();
        Thread.sleep(2000);
        
    }

    @Then("user should see order confirmation message")
    public void ordConf() {
    	
        WebElement msg = driver.findElement(By.className("complete-header"));
        System.out.println("Order Confirmation: " + msg.getText());
        Assert.assertTrue(msg.isDisplayed());
        
        test.pass("Order Confirmation Displayed: " + msg.getText());
        
    }

    @Then("user should see item prices and totals match correctly")
    public void ValDet() throws InterruptedException {
    	
        List<WebElement> prices = driver.findElements(By.className("inventory_item_price"));
        
        double sum = 0;
        
        for (WebElement price : prices) {
            sum += Double.parseDouble(price.getText().replace("$", ""));
        }
        
        Thread.sleep(2000);
        String totalText = driver.findElement(By.className("summary_subtotal_label")).getText();
        double displayedTotal = Double.parseDouble(totalText.replace("Item total: $", ""));
        System.out.println("Calculated Total: " + sum + " | Displayed Total: " + displayedTotal);
        
        Assert.assertEquals(displayedTotal, sum);
        
        test.pass("Price accuracy validated successfully");
    }

    @When("user leaves mandatory checkout fields empty")
    public void leaveEmpty() {
        // nothing to c here
    }

    @Then("user should see validation error messages")
    public void valErr() {
    	
    	driver.findElement(By.id("continue")).click();
        WebElement error = driver.findElement(By.cssSelector("h3[data-test='error']"));
        System.out.println("Validation Error: " + error.getText());
        Assert.assertTrue(error.isDisplayed());
        
        test.fail("Validation Error displayed: " + error.getText());
        
    }
    
    @After
    public void closeUp() throws InterruptedException {
    	
        Thread.sleep(2000);
        extent.flush();
	    driver.quit();
	    
    }

}
