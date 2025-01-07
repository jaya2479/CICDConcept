package AutomationPractice.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationPractice.AbstractClasses.AbstractMethods;

public class ProductsHomePage extends AbstractMethods{

	WebDriver driver;

	public ProductsHomePage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".mb-3")
	List<WebElement> listOfProducts;
	By ProductsList = By.cssSelector(".mb-3");
	By addProdToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	@FindBy(css = ".ng-animating")
	WebElement Buffer;
	@FindBy(css = ".fa-sign-out")
	WebElement signOut;

	public List<WebElement> getListOfProds()
	{
		waitForElementToAppear(ProductsList);
		return listOfProducts;
	}

	public WebElement getProductName(String productToAdd)
	{
		WebElement prodName = getListOfProds().stream().filter(sProduct->
	      sProduct.findElement(By.cssSelector("b")).getText().equals(productToAdd)).findFirst().orElse(null);
	    return prodName;
	}

	public void addToCart(String productToAdd) throws InterruptedException
	{
		WebElement prodName = getProductName(productToAdd);
		prodName.findElement(addProdToCart).click();
		Thread.sleep(4000);
//		waitForElementToAppear(toastMessage);
//		waitForElementToDisappear(Buffer);

	}

	public void signOutBtn()
	{
		signOut.click();
	}


}
