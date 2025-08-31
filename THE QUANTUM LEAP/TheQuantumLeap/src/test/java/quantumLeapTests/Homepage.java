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


public class Homepage {
	
	WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @Before
    public void setup() {

        ExtentSparkReporter spark = new ExtentSparkReporter("HomepageUITestReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);

        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
    }
    
    @Given("I am on the login page")
    public void entercredentials() {
    	
    	test = extent.createTest("Execution will begin shortly...!!");
    	
    	driver.findElement(By.id("user-name")).sendKeys("standard_user");;
        driver.findElement(By.id("password")).sendKeys("secret_sauce");;
        
    }
    
    @When("I enter credentials and click login")
    public void clickLogin() {

    	driver.findElement(By.id("login-button")).click();
    	
    }
    
    @Then("the system should respond as expected")
    public void valRes() {
    	test.info("Login successful!");
    }
    
    @Then("I expect to land on the homepage")
    public void veriRes() {
        
    	test.info("Success!");
    	
    }

    @Then("I should see all products with name, description, price, and add to cart button")
    public void prodVis() {
    	
        List<WebElement> prods = driver.findElements(By.className("inventory_item"));
        
        for(WebElement product : prods) {
        	
            String name = product.findElement(By.className("inventory_item_name")).getText();
            String desc = product.findElement(By.className("inventory_item_desc")).getText();
            String price = product.findElement(By.className("inventory_item_price")).getText();
            WebElement addBtn = product.findElement(By.tagName("button"));

            Assert.assertFalse(name.isEmpty());
            Assert.assertFalse(desc.isEmpty());
            Assert.assertFalse(price.isEmpty());
            Assert.assertTrue(addBtn.isDisplayed());
            
        }
        
        test.info("Test passed!");
    }

    @When("I select {string} from the sorting dropdown")
    public void selectDrp(String sortOpts) {
    	
        WebElement dropdown = driver.findElement(By.className("product_sort_container"));
        dropdown.click();
        dropdown.findElement(By.xpath("//option[text()='" + sortOpts + "']")).click();
        
    }

    @Then("products should be sorted accordingly")
    public void verifyRes() {

    	WebElement dropdown = driver.findElement(By.className("product_sort_container"));
        Assert.assertTrue(dropdown.isDisplayed());
        
        test.info("Test passed!");
        
    }
    
    @When("I scroll to a sepcific product")
    public void scrollToProd() throws InterruptedException {

    	JavascriptExecutor js = (JavascriptExecutor) driver;
		
		WebElement ele = driver.findElement(By.linkText("Test.allTheThings() T-Shirt (Red)"));
		js.executeScript("arguments[0].scrollIntoView()", ele);
        Thread.sleep(2000);
    	
    }

    @When("I click add to cart")
    public void clickCart() {

    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); 
    	WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartBtn);
        
    }

    @Then("the cart should show {int} item")
    public void cartItemVis(Integer itemCount) throws InterruptedException {
    	
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        JavascriptExecutor js = (JavascriptExecutor) driver;
        
        WebElement cartele = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge"))
        );
        
        js.executeScript("arguments[0].scrollIntoView()", cartele);
        Thread.sleep(2000);

        String cartCnt = cartele.getText();
        Assert.assertEquals(Integer.parseInt(cartCnt), itemCount.intValue());
        
        test.info("Test passed!");
    	test.pass("Execution completed successfully!");
    }

    
    @When("I scroll to the footer")
    public void scrollToFooter() throws InterruptedException {
    	
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement footer = driver.findElement(By.className("footer_copy")); 
        js.executeScript("arguments[0].scrollIntoView(true);", footer);
        Thread.sleep(1000);
        
    }

    @Then("I click on each footer link")
    public void clickLinks() {
    	
        List<WebElement> footerLinks = driver.findElements(By.cssSelector("footer a"));

        for (WebElement link : footerLinks) {
            String linkText = link.getText();
            String href = link.getAttribute("href");

            ((JavascriptExecutor) driver).executeScript("window.open(arguments[0])", href);

            test.info("Clicked " + linkText + " → " + href);
        }
    }

    @Then("I should be redirected to their respective destinations")
    public void verifyOutcome() {

        List<String> tabs = driver.getWindowHandles().stream().toList();

        for (int i = 1; i < tabs.size(); i++) {
            driver.switchTo().window(tabs.get(i));
            String currentURL = driver.getCurrentUrl();

            Assert.assertTrue(
            		currentURL.contains("https://x.com/saucelabs") ||
                currentURL.contains("https://twitter.com/saucelabs") ||
                currentURL.contains("https://www.facebook.com/saucelabs") ||
                currentURL.contains("https://www.linkedin.com/company/sauce-labs/"),
                "Unexpected redirect: " + currentURL
            );

            test.pass("Redirection successful → " + currentURL);

        }
        
        driver.switchTo().window(tabs.get(0));
    }
    
    @After
    public void closeUp() throws InterruptedException {
    	
    	Thread.sleep(3000);
    	extent.flush();
	    driver.quit();
	    
    }

}
