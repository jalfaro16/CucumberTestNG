package Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pageObject.CartPage;
import pageObject.CheckOutPage;
import pageObject.ConfirmationPage;
import pageObject.LandingPage;
import pageObject.ProductCatalog;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Test3 {
	
	public static void main(String[]args) throws InterruptedException
{
    String productName = "IPHONE 13 PRO";		
	WebDriverManager.chromedriver().setup();
	WebDriver driver = new ChromeDriver();	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	//driver.manage().window().maximize();
	
	LandingPage landingPage = new LandingPage(driver);
	landingPage.goTo();
	ProductCatalog prodCat = landingPage.loginApp("anshika@gmail.com","Iamking@000");
	List<WebElement> products = prodCat.getProductList();	
	//Search and select desire product//** Add product to Cart
	prodCat.addProducToCart(productName);
	
	//Go to cart screen//
	CartPage cartPage = prodCat.goToCartPage();
	
	//Verify Elements presents in the screen list//
	Boolean match = cartPage.VerifyProductDisplay(productName);
	Assert.assertTrue(match);

	CheckOutPage checkoutPage =  cartPage.goToCheckout();
	checkoutPage.selectCountry("India");
	
	ConfirmationPage confirmationPage = checkoutPage.submitOrder();
	
	//Verify confirmation message///
	String confirmationmessage = confirmationPage.getConfirmationMsg();
	Assert.assertTrue(confirmationmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	System.out.println(confirmationmessage);
	driver.close();
}

}
