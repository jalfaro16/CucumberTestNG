package stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.CartPage;
import pageObject.CheckOutPage;
import pageObject.ConfirmationPage;
import pageObject.LandingPage;
import pageObject.ProductCatalog;
import testComponents.BaseTest;

public class stepDefImplement extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalog prodCat;
	public ConfirmationPage confirmationPage;
	
	
	@Given("I landed on Ecomerce Page")
	public void I_landed_on_Ecomerce_Page() throws IOException
	{
		landingPage = launchApp();
	}

	@Given ("^Logged in with username (.+) and (.+)$")
	public void Logged_in_with_username_name_and_password(String userName, String password)
	{
		prodCat = landingPage.loginApp(userName, password);
	}
	
	@When("^I add a (.+) to Cart$")
	public void I_add_a_product_to_cart(String productName)
	{
		List<WebElement> products = prodCat.getProductList();	
		//Search and select desire product//** Add product to Cart
		prodCat.addProducToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void Checkout_submit_order(String productName)
	{
		CartPage cartPage = prodCat.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkoutPage =  cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
	}
	
	@Then("{string} mesagge is displayed on conf page")
	public void verifyMessage(String string)
	{
		String confirmationmessage = confirmationPage.getConfirmationMsg();
		Assert.assertTrue(confirmationmessage.equalsIgnoreCase(string));
		System.out.println(confirmationmessage);
		driver.close();
	}
	
	@Then("^\"([^\"]*)\" message is displayed")
	public void verifyMessageafterbadLogIn(String arg1)
	{
		Assert.assertEquals(arg1, landingPage.getErrorMsg());
		driver.close();
	}
	
	
	
	//And Checkout <productName>
	//Then "THANKYOU FOR THE ORDER." mesagge is displayed on conf page
	
	
	
	
	
	
	
	
	
	
	
}
