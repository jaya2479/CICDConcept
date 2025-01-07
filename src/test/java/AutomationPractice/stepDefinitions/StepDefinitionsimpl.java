package AutomationPractice.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import AutomationPractice.Pages.CartPage;
import AutomationPractice.Pages.CheckoutPage;
import AutomationPractice.Pages.ConfirmationPage;
import AutomationPractice.Pages.LoginPage;
import AutomationPractice.Pages.ProductsHomePage;
import AutomationPractice.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionsimpl extends BaseTest {

	public LoginPage loginPage;
	public ProductsHomePage prodHomePage;
	public CartPage cartPage;
	public ConfirmationPage confirm;
	
	@Given("Launch the Ecommerce Application")
	public void launch_the_ecommerce_application() throws IOException {
		
		loginPage = launchingPage();

	}
	
	@Given("^I want to Login with username (.+) and password (.+)$")
	public void i_want_to_login_with_username_and_password(String email, String password) {
		prodHomePage = loginPage.credentials(email, password );
	}

	@When("^I added the product (.+) to the cart$")
	public void i_added_the_product_to_the_cart(String product) throws InterruptedException {
		
		  prodHomePage.addToCart(product);	      
	}
	 
	@And ("^Checkout (.+) and submit the Order$")
	public void checkout_and_submit_the_order(String product) {
		cartPage = prodHomePage.goToCartPage();
	      Boolean match = cartPage.verifyCartProduct(product);
	      Assert.assertTrue(match);
	      CheckoutPage checkOut =  cartPage.submitOrder();
		  checkOut.placeOrderPgae("India");
		  confirm = checkOut.Submit();
	}
	
	@Then("{string} message is displayed in the confirmation page")
	public void message_is_displayed_in_the_confirmation_page(String string) {
		  String confirmationMsg = confirm.confirmationMessgae();
	      Assert.assertTrue(confirmationMsg.equalsIgnoreCase(string));
	      System.out.println(confirmationMsg);
	      driver.close();
	}
	
	@Then("Error Message {string} is displayed")
	public void error_message_is_displayed(String string) {
		
        Assert.assertEquals(string, "Incorrect email or password.");
        driver.close();

	}
}
