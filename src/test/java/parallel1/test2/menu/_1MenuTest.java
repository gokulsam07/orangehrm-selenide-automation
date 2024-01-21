package parallel1.test2.menu;

import static com.codeborne.selenide.Selenide.open;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import orangehrm.po.homepage.HomePage;
import orangehrm.po.login.LoginPage;

public class _1MenuTest {
	private HomePage homepage;
	@BeforeClass
	public void login() {
		open("/login");
		new LoginPage().enterCredentialsAndSubmit();
		homepage = new HomePage().isUserLoggedIn();
	}
	
	@Test
	public void _2menuSearchValidation() {
		homepage .new MenuComponent().searchMenu("A");
		Assert.assertTrue(homepage .new MenuComponent().validateSearchResults("A"), "Search results doesn't match"); 
	}
	
	@Test
	public void _1expandAndShrinkMenuValidation() {
		homepage .new MenuComponent().shrinkMenu();
		Assert.assertTrue(homepage .new MenuComponent().isMenuShrunk(), "Menu is not shrunk"); 
		homepage .new MenuComponent().expandMenu();
		Assert.assertTrue(homepage .new MenuComponent().isMenuExpanded(), "Menu is not open"); 
		
	}
	@AfterClass
	public void logout() {
		new HomePage().logout();
	}
}
