package AutomationPractice.MainTests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AutomationPractice.DataSet.DataReader;
import AutomationPractice.Pages.CartPage;
import AutomationPractice.Pages.CheckoutPage;
import AutomationPractice.Pages.ConfirmationPage;
import AutomationPractice.Pages.ProductsHomePage;
import AutomationPractice.TestComponents.BaseTest;

public class OrdersTest extends BaseTest{

	 // String productToAdd = "ADIDAS ORIGINAL";

	@Test(dataProvider="getData", groups = "PlaceOrder")
	public void SubmitOrder(HashMap<String, String> input) throws IOException, InterruptedException
	{
	  //LoginPage loginPage = launchingPage();
      ProductsHomePage prodHomePage = loginPage.credentials(input.get("Email"), input.get("Password"));
      prodHomePage.addToCart(input.get("ProductToAdd"));
      CartPage cartPage = prodHomePage.goToCartPage();
      Boolean match = cartPage.verifyCartProduct(input.get("ProductToAdd"));
      Assert.assertTrue(match);
      CheckoutPage checkOut =  cartPage.submitOrder();
	  checkOut.placeOrderPgae("India");
	  ConfirmationPage confirm = checkOut.Submit();
	  String confirmationMsg = confirm.confirmationMessgae();
      Assert.assertTrue(confirmationMsg.equalsIgnoreCase("Thankyou for the order."));
      System.out.println(confirmationMsg);

	}

	@Test
	public void loginLogoutMsgs() throws IOException, InterruptedException
	{
		//LoginPage loginPage = launchingPage();
		ProductsHomePage prodHomePage = loginPage.credentials("djaya0110@gmail.com", "Dj@12345");
	    String loginConfirmMessage = loginPage.loginConfirmationMsg();
	    System.out.println(loginConfirmMessage);
	    Assert.assertEquals(loginConfirmMessage, "Login Successfully");
	    //Assert.assertTrue(loginConfirmMessage.equalsIgnoreCase("Login Successfully"));
	    // prodHomePage.signOutBtn();
	    // String logoutConfirmMessage = loginPage.logOutConfirmationMsg();
	    // System.out.println(logoutConfirmMessage);
	    // Assert.assertEquals(logoutConfirmMessage, "Logout Successfully");
	    //Assert.assertTrue(logoutConfirmMessage.equalsIgnoreCase("Logout Successfully"));
	}


	@DataProvider
	public Object[][] getData() throws IOException
	{
		DataReader getJsonData = new DataReader();
		List<HashMap<String, String>> data = getJsonData.getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\AutomationPractice\\DataSet\\LoginCredAndProdcutDetails.json");
		return new Object[][]{{data.get(0)}, {data.get(1)}};
	}












//	@DataProvider
//	public Object[][] getData()
//	{
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "djaya0110@gmail.com");
//		map.put("pwd", "Dj@12345");
//		map.put("productToAdd", "ADIDAS ORIGINAL");
//
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "shetty@gmail.com");
//		map1.put("pwd", "Iamking@000");
//		map1.put("productToAdd", "QWERTY");
//
//		return new Object[][]{{map}, {map1}};
//	}

//	@DataProvider
//	public Object[][] getData()
//	{
//		return new Object[][]{{"djaya0110@gmail.com","Dj@12345","ADIDAS ORIGINAL"}, {"shetty@gmail.com","Iamking@000","QWERTY"}};
//	}


}
