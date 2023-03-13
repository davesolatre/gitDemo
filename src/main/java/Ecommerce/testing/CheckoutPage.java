package Ecommerce.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Ecommerce.AbstactComponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".action__submit")
	private WebElement placeOrderutton;

	@FindBy(css = "[placeholder='Select Country']")
	private WebElement country;

	@FindBy(css = "span[class='ng-star-inserted']")
	private WebElement selectCountry;

	private By results = By.cssSelector(".ta-results");

	public void SelectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(results);
		selectCountry.click();

	}
	public ConfirmationPage SubmitOrder() {
		placeOrderutton.click();
		return new ConfirmationPage(driver);
	}

}
