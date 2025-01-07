package AutomationPractice.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import AutomationPractice.AbstractClasses.AbstractMethods;

public class OrdersPage extends AbstractMethods{
WebDriver driver;

	public OrdersPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}



}
