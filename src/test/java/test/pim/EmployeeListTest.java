package test.pim;

import static com.codeborne.selenide.Selenide.open;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import orangehrm.po.homepage.HomePage;
import orangehrm.po.pim.PIMEmployeeListPage;

public class EmployeeListTest {
	private HomePage homepage;

	@BeforeMethod
	public void login() {
		open("/login");
		homepage = new HomePage().isUserLoggedIn();
	}

	@Test(priority = 1)
	public void validateEmployeeCountTest() {
		String menu = "PIM";
		PIMEmployeeListPage pim = (PIMEmployeeListPage) homepage.new MenuComponent().clickMenu(menu);
		pim.validateCurrentMenu(menu);
		Assert.assertTrue(pim.validateSearchResultAndSize(), "Expected size didn't match with actual size");
	}

	@Test(priority = 2)
	public void validateSearchTest() {
		String menu = "PIM";
		String searchString = "Ad";
		PIMEmployeeListPage pim = (PIMEmployeeListPage) homepage.new MenuComponent().clickMenu(menu);
		pim.setEmployeeName(searchString);
		pim.searchDetails();
		Assert.assertTrue(pim.validateSearchResults(), "Expected size didn't match with actual size");
	}

	@Test(priority = 3)
	public void validateNoResultToastTest() throws InterruptedException {
		String menu = "PIM";
		String searchString = "zoo";
		PIMEmployeeListPage pim = (PIMEmployeeListPage) homepage.new MenuComponent().clickMenu(menu);
		pim.setEmployeeName(searchString);
		pim.searchDetails();		
		Assert.assertTrue(pim.validateNoRecordToast(), "Cannot find the No Records Toast");
	}
	@Test(priority = 4)
	public void searchBySubUnitTest() throws InterruptedException {
		String menu = "PIM";
		String fieldName = "Sub Unit";
		String option = "Quality Assurance";
		PIMEmployeeListPage pim = (PIMEmployeeListPage) homepage.new MenuComponent().clickMenu(menu);
		pim.selectDropDownOptionForField(fieldName,option);
		pim.searchDetails();
		Assert.assertTrue(pim.validateSubUnitDetails(fieldName), "Search results have a unmatching sub unit");
	}
	@Test(priority = 5)
	public void searchByJobTitleTest() throws InterruptedException {
		String menu = "PIM";
		String fieldName = "Job Title";
		String option = "Software Engineer";
		PIMEmployeeListPage pim = (PIMEmployeeListPage) homepage.new MenuComponent().clickMenu(menu);
		pim.selectDropDownOptionForField(fieldName,option);
		pim.searchDetails();
		Assert.assertTrue(pim.validatejobTitleDetails(fieldName), "Search results have a unmatching job title");
	}
}

