package Tests;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.CartPage;
import pageObject.CheckOutPage;
import pageObject.ConfirmationPage;
import pageObject.ProductCatalog;
import testComponents.BaseTest;
import testComponents.Retry;

public class ErrorValidation extends BaseTest{
	
	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation()
	{
		String Expected_msg = "Incorrect email or password.";
		//String productName = "IPHONE 13 PR";
		landingPage.loginApp("anshika@gmail.com","Iamking@0");
		
		Assert.assertEquals(Expected_msg, landingPage.getErrorMsg());
	}
	
	@Test
	public void ProductErrorValidation()
	{
		String productName = "IPHONE 13 PRO";	
		ProductCatalog prodCat = landingPage
				.loginApp("anshika@gmail.com","Iamking@000");
		List<WebElement> products = prodCat.getProductList();	
		
		prodCat.addProducToCart(productName);
		
		CartPage cartPage = prodCat.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);

		
	}
	
}
