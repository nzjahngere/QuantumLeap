package quantumLeapTests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class Cart {
	
	WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @Before
    public void setup() {
    	
        ExtentSparkReporter spark = new ExtentSparkReporter("CartTestReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);

        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    
    @Given("I am on login page")
    public void entercredentials() {
    	
        test = extent.createTest("Execution will begin shortly...!!");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        
    }
    
    @When("I fill credentials and click login")
    public void clickLogin() {
    	
        driver.findElement(By.id("login-button")).click();
        
    }
    
    @Then("the system must respond as expected")
    public void valRes() {
    	
        test.info("Login successful!");
        
    }
    
    @Then("I expect to land on homepage")
    public void veriRes() {
    	
        test.info("Success!");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
        
    }
    
    @When("I add a product to the cart")
    public void addItem() throws InterruptedException {
    	
     WebElement addBtn = driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)"));
     ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);
        
    }

    @Then("the cart must show {int} item")
    public void checkIcon(Integer cnt) throws InterruptedException {
    	
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        JavascriptExecutor js = (JavascriptExecutor) driver;
        
        WebElement cartele = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge"))
        );
        
        js.executeScript("arguments[0].scrollIntoView()", cartele);
        Thread.sleep(2000);

        String cartCnt = cartele.getText();
        Assert.assertEquals(Integer.parseInt(cartCnt), cnt.intValue());
        
        test.info("Test passed!");
    	test.pass("Execution completed successfully!");
        
    }

    @When("I add all products to the cart")
    public void addAllProducts() {
    	
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        int totalProducts = driver.findElements(By.cssSelector(".inventory_item")).size();

        for (int i = 0; i < totalProducts; i++) {
            WebElement btn = driver.findElement(By.xpath("//button[contains(@id,'add-to-cart')]"));
            wait.until(ExpectedConditions.elementToBeClickable(btn));
            btn.click();

            wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    By.className("shopping_cart_badge"),
                    String.valueOf(i + 1)
            ));
        }

        test.info("All " + totalProducts + " products added to cart successfully");
    }


    @Then("the cart should display the correct total number of items")
    public void crtCnt() throws InterruptedException {
            	
        int totalProducts = driver.findElements(By.cssSelector(".inventory_item")).size();
        String cartCnt = driver.findElement(By.className("shopping_cart_badge")).getText();
        
        Assert.assertEquals(String.valueOf(totalProducts), cartCnt);
        test.pass("Cart badge shows correct total: " + cartCnt + " items.");
    }


    @Then("I open the cart")
    public void openCart() {
        
        driver.findElement(By.className("shopping_cart_link")).click();
        test.info("Cart opened successfully.");
    }

    @Then("I should see all selected products listed in the cart")
    public void prosInCart() {
        
        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
        int cartSize = cartItems.size();
        
        Assert.assertEquals(cartSize, 6, "Mismatch in number of items inside cart!");
        
        for (WebElement item : cartItems) {
            String productName = item.findElement(By.className("inventory_item_name")).getText();
            test.info("Product verified in cart: " + productName);
        }
        
        test.pass("All selected products are present in the cart.");
    }

    @Given("I have added a product to the cart")
    public void addToCartPrecondition() throws InterruptedException {
    	
    	Thread.sleep(2000);
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        
        test.info("Sauce Labs Fleece Jacket added to cart");
        
    }

    @When("I remove it from the cart")
    public void remItem() throws InterruptedException {
    	
    	Thread.sleep(2000);
        driver.findElement(By.className("shopping_cart_link")).click();
        
        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
        
        for (WebElement item : cartItems) {
            String name = item.findElement(By.className("inventory_item_name")).getText();
            if (name.equals("Sauce Labs Fleece Jacket")) {
                item.findElement(By.tagName("button")).click();
                test.info("Removed Sauce Labs Fleece Jacket from cart");
                break;
            }
        }
        
        
    }

    @Then("the cart should be empty")
    public void verifyEmptyCart() {
    	
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isInvisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(
            By.className("shopping_cart_badge")
        ));
        
        Assert.assertTrue(isInvisible, "Cart must be empty!");
        test.pass("Cart is empty as expected.");
        
    }

    
    @After
    public void closeUp() throws InterruptedException {
    	
        Thread.sleep(2000);
        extent.flush();
	    driver.quit();
	    
    }
    
}
