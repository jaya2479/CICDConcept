package AutomationPractice.Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationPractice.AbstractClasses.AbstractMethods;

public class CartPage extends AbstractMethods{
WebDriver driver;

	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(css=".cartSection h3")
	private List<WebElement> cartProducts;

	@FindBy(css=".totalRow button")
	WebElement submit;

	public Boolean verifyCartProduct(String productToAdd)
	{
	      Boolean match = cartProducts.stream().anyMatch(cartProd ->
	      cartProd.getText().equalsIgnoreCase(productToAdd));
	      return match;
	}
	public CheckoutPage submitOrder()
	{
		submit.click();
		return new CheckoutPage(driver);

	}


}
