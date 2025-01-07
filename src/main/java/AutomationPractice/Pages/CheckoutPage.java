package AutomationPractice.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationPractice.AbstractClasses.AbstractMethods;

public class CheckoutPage extends AbstractMethods{

	WebDriver driver;

	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="[placeholder='Select Country']")
	WebElement enterCountry;

	@FindBy(xpath="//*[text()=' India']")
	WebElement selectCountry;

	@FindBy(xpath="//*[text()='Place Order ']")
	WebElement placeOrder;

	By countryNames = By.cssSelector(".ta-results");

    public void placeOrderPgae(String CountryName)
    {
    	enterCountry.sendKeys(CountryName);
    	waitForElementToAppear(countryNames);
        selectCountry.click();

    }

    public ConfirmationPage Submit()
    {
    	placeOrder.click();
    	return new ConfirmationPage(driver);
    }


}
