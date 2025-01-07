package AutomationPractice.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationPractice.AbstractClasses.AbstractMethods;

public class LoginPage extends AbstractMethods{

	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement emailId;
	@FindBy(id = "userPassword")
	WebElement password;
	@FindBy(id = "login")
	WebElement login;
	@FindBy(css=".ng-trigger-flyInOut")
	WebElement errorMsg;
	@FindBy(css = "div[aria-label='Login Successfully']")
	WebElement loginConfirm;
	@FindBy(css = "div[aria-label='Logout Successfully']")
	WebElement logoutConfirm;

	public ProductsHomePage credentials(String email, String pwd)
	{
		emailId.sendKeys(email);
		password.sendKeys(pwd);
		login.click();
        ProductsHomePage prodHomePage = new ProductsHomePage(driver);
        return prodHomePage;
	}

	public String loginWithWrongCred()
	{
		return errorMsg.getText();

	}

	public String loginConfirmationMsg()
	{
		return loginConfirm.getText();
	}

	public String logOutConfirmationMsg()
	{
		return logoutConfirm.getText();
	}

	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
}
