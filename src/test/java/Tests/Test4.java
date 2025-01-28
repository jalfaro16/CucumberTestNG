package Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.CartPage;
import pageObject.CheckOutPage;
import pageObject.ConfirmationPage;
import pageObject.LandingPage;
import pageObject.OrderPage;
import pageObject.ProductCatalog;
import io.github.bonigarcia.wdm.WebDriverManager;
import testComponents.BaseTest;

public class Test4 extends BaseTest {
	String productName = "ADIDAS ORIGINAL";	
	
	@Test(dataProvider="getData",groups= {"Purshase"})
	public void submitOrder(HashMap<String,String>input) throws IOException
	{	
    //LandingPage landingPage=launchApp();
	ProductCatalog prodCat = landingPage
			.loginApp(input.get("username"),input.get("password"));
	List<WebElement> products = prodCat.getProductList();	
	//Search and select desire product//** Add product to Cart
	prodCat.addProducToCart(input.get("productname"));
	
	//Go to cart screen//
	CartPage cartPage = prodCat.goToCartPage();
	
	//Verify Elements presents in the screen list//
	Boolean match = cartPage.VerifyProductDisplay(input.get("productname"));
	Assert.assertTrue(match);

	CheckOutPage checkoutPage =  cartPage.goToCheckout();
	checkoutPage.selectCountry(input.get("country"));
	ConfirmationPage confirmationPage = checkoutPage.submitOrder();
	
	//Verify confirmation message///
	String confirmationmessage = confirmationPage.getConfirmationMsg();
	Assert.assertTrue(confirmationmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	System.out.println(confirmationmessage);
	//driver.close();
    }
	
	@Test(dependsOnMethods={"submitOrder"})
	public void OrderHistory()
	{
		ProductCatalog prodCat = landingPage
				.loginApp("shetty@gmail.com","Iamking@000");
		OrderPage ordersPage = prodCat.goToOrderPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty
		("user.dir")+
		"//src//test//java//JavaSeleniumTest//data//PurshaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)} };
	}
	
}
