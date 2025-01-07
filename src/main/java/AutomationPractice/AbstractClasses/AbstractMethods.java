package AutomationPractice.AbstractClasses;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AutomationPractice.Pages.CartPage;
import AutomationPractice.Pages.OrdersPage;

public class AbstractMethods {

	WebDriver driver;
	public AbstractMethods(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="[routerlink = '/dashboard/cart']")
	WebElement cartButton;

	@FindBy(css=".btn.btn-custom[routerlink='/dashboard/myorders']")
    WebElement ordersButton;

	public void waitForElementToAppear(By findBy)
	{
	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	 wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));
	}

	public void waitForElementToDisappear(WebElement spin)
	{
	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	 wait.until(ExpectedConditions.invisibilityOf(spin));
	}

	public CartPage goToCartPage()
	{
		cartButton.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}

	public OrdersPage goToOrdersPage()
	{
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.elementToBeClickable(ordersButton)).click();
		ordersButton.click();
		OrdersPage orders = new OrdersPage(driver);
		return orders;
	}
}
