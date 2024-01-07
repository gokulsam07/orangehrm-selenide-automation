package test.login;

import static com.codeborne.selenide.Selenide.open;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import orangehrm.po.homepage.HomePage;
import orangehrm.po.login.LoginPage;

public class LoginTest {
	
	@BeforeMethod
	public void openApp() {
		open("/login");
	}
	@Test
	public void testLogin() {
		new LoginPage().enterCredentialsAndSubmit();
		new HomePage().isUserLoggedIn();
	}

}
