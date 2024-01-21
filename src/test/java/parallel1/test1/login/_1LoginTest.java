package parallel1.test1.login;

import static com.codeborne.selenide.Selenide.open;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import orangehrm.po.homepage.HomePage;
import orangehrm.po.login.LoginPage;

public class _1LoginTest {

	@BeforeClass
	public void openApp() {
		open("/login");
	}

	@Test
	public void _1testLoginInvalid() {
		new LoginPage().enterInvalidCredentialsAndSubmit();
	}

	@Test
	public void _2testLogin() {
		new LoginPage().enterCredentialsAndSubmit();
		new HomePage().isUserLoggedIn();
	}
	@AfterClass
	public void logout() {
		new HomePage().logout();
	}

}
