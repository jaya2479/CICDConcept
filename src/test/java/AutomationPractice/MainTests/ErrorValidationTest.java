package AutomationPractice.MainTests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import AutomationPractice.TestComponents.BaseTest;

public class ErrorValidationTest extends BaseTest{

	@Test(groups = "ErrorValidations")
	public void ErrorValidations() throws IOException
	{
		//LoginPage loginPage = launchingPage();
		loginPage.credentials("djaya0110@gmail.com", "D@1234");
		String error = loginPage.loginWithWrongCred();
        Assert.assertEquals(error, "Incorrect email or password.");
	}

}
