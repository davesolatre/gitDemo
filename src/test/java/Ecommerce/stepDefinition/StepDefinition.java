package Ecommerce.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Ecommerce.testComponent.BaseTest;
import Ecommerce.testing.CartPage;
import Ecommerce.testing.CheckoutPage;
import Ecommerce.testing.ConfirmationPage;
import Ecommerce.testing.LandingPage;
import Ecommerce.testing.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page")
	public void I_Landed_on_Ecommerce_Page() throws IOException {
		
		//
		landingPage = lunchApplication();
	}
	@Given("^I logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) {
		
		productCatalogue = landingPage.LoginApplication(username, password);
		
	}
	@When("^I add the product (.+) to cart%")
	public void add_the_productname_to_cart(String productName) throws InterruptedException {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addToCart(productName);
		
	}
	
	
	 @When("^I checkout (.+) and submit order$")
	 public void checkout_productname_and_submit_order(String productName) {
		 CartPage cartPage = productCatalogue.goToCartPage();
			Boolean match = cartPage.verifyProductName(productName);
			Assert.assertTrue(match);
			CheckoutPage checkoutPage = cartPage.goToCheckOut();
			checkoutPage.SelectCountry("Philippines");
			confirmationPage = checkoutPage.SubmitOrder();
	 }
	 
	 //Then "THANK YOU FOR THE ORDER." message is displayed on ConfirmationPage
	 
	 @Then("{string} message is displayed on ConfirmationPage")
	 public void message_is_displayed_confirmationpage(String string) {
		 String confirmMessage = confirmationPage.getConfirmationMessage();
			Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
	 }
}
