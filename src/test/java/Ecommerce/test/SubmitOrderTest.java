package Ecommerce.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Ecommerce.testComponent.BaseTest;
import Ecommerce.testing.CartPage;
import Ecommerce.testing.CheckoutPage;
import Ecommerce.testing.ConfirmationPage;
import Ecommerce.testing.OrderPage;
import Ecommerce.testing.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {

		ProductCatalogue productCatalogue = landingPage.LoginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductName(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckOut();
		checkoutPage.SelectCountry("Philippines");
		ConfirmationPage confirmationPage = checkoutPage.SubmitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.LoginApplication("dsolatre@yahoo.com", "Dsolatre16");
		OrderPage orderPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));

	}

	

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\Ecommerce\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

}

/*
 * HashMap<String,String> map = new HashMap<String,String>();
 * map.put("email","dsolatre@yahoo.com"); map.put("password", "Dsolatre16");
 * map.put("productName", "ZARA COAT 3");
 * 
 * HashMap<String,String> map1 = new HashMap<String,String>();
 * map1.put("email","dave@yahoo.com"); map1.put("password", "Dsolatre16");
 * map1.put("productName", "ADIDAS ORIGINAL");
 */