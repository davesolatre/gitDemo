package Ecommerce.test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


import Ecommerce.testComponent.BaseTest;
import Ecommerce.testComponent.Retry;
import Ecommerce.testing.CartPage;
import Ecommerce.testing.ProductCatalogue;

public class ErrorValidationTest extends BaseTest{

	
	@Test(groups= {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws InterruptedException, IOException
	{
		String productName = "ZARA COAT 3";
	
		ProductCatalogue productCatalogue = landingPage.LoginApplication("dasolatre@yahoo.com", "Dssolatre16");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage()) ;
	}
	
	@Test
	public void ProductErrorValidation() throws InterruptedException, IOException
	{
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.LoginApplication("dsolatre@yahoo.com", "Dsolatre16");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductName("ZARA COAT 33");
		Assert.assertFalse(match);
	

	}

}
