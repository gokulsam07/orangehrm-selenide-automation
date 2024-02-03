package parallel1.test2.menu;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class _1MenuTest extends BaseTest{
	
	@Test
	public void _2menuSearchValidation() {
		homePage .new MenuComponent().searchMenu("A");
		Assert.assertTrue(homePage .new MenuComponent().validateSearchResults("A"), "Search results doesn't match"); 
	}
	
	@Test
	public void _1expandAndShrinkMenuValidation() {
		homePage .new MenuComponent().shrinkMenu();
		Assert.assertTrue(homePage .new MenuComponent().isMenuShrunk(), "Menu is not shrunk"); 
		homePage .new MenuComponent().expandMenu();
		Assert.assertTrue(homePage .new MenuComponent().isMenuExpanded(), "Menu is not open"); 
		
	}
}
