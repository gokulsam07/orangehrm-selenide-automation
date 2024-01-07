package test.menu;

import static com.codeborne.selenide.Selenide.open;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import orangehrm.po.homepage.HomePage;
import orangehrm.po.login.LoginPage;

public class MenuTest {
	private HomePage homepage;
	@BeforeMethod
	public void login() {
		open("/login");
		homepage = new HomePage().isUserLoggedIn();
	}
	
	@Test(priority=2)
	public void menuSearchValidation() {
		homepage .new MenuComponent().searchMenu("A");
		Assert.assertTrue(homepage .new MenuComponent().validateSearchResults("A"), "Search results doesn't match"); 
	}
	
	@Test(priority=1)
	public void expandAndShrinkMenuValidation() {
		homepage .new MenuComponent().shrinkMenu();
		Assert.assertTrue(homepage .new MenuComponent().isMenuShrunk(), "Menu is not shrunk"); 
		homepage .new MenuComponent().expandMenu();
		Assert.assertTrue(homepage .new MenuComponent().isMenuExpanded(), "Menu is not open"); 
		
	}

}
