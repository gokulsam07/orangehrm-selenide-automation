package parallel3.test.pim;

import static com.codeborne.selenide.Selenide.open;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import orangehrm.po.homepage.HomePage;
import orangehrm.po.login.LoginPage;
import orangehrm.po.pim.PIMEmployeeListPage;

public class _2EmployeeListTest {
	private HomePage homepage;
	private String menu = "PIM";
	private String searchString="";
	private String fieldName = "";
	private String option = "";

	@BeforeClass
	public void login() {
		open("/login");
		new LoginPage().enterCredentialsAndSubmit();
		homepage = new HomePage().isUserLoggedIn();
	}

	@Test
	public void _1validateEmployeeCountTest() {
		PIMEmployeeListPage pim = (PIMEmployeeListPage) homepage.new MenuComponent().clickMenu(menu);
		pim.validateCurrentMenu(menu);
		Assert.assertTrue(pim.validateSearchResultAndSize(), "Expected size didn't match with actual size");
	}

	@Test
	public void _2validateSearchTest() {
		searchString = "Ad";
		PIMEmployeeListPage pim = (PIMEmployeeListPage) homepage.new MenuComponent().clickMenu(menu);
		pim.setEmployeeName(searchString);
		pim.searchDetails();
		Assert.assertTrue(pim.validateSearchResults(), "Expected size didn't match with actual size");
	}

	@Test
	public void _3validateNoResultToastTest() throws InterruptedException {
		searchString = "zoo";
		PIMEmployeeListPage pim = (PIMEmployeeListPage) homepage.new MenuComponent().clickMenu(menu);
		pim.setEmployeeName(searchString);
		pim.searchDetails();		
		Assert.assertTrue(pim.validateNoRecordToast(), "Cannot find the No Records Toast");
	}
	@Test
	public void _4searchBySubUnitTest() throws InterruptedException {
		fieldName = "Sub Unit";
		option = "Quality Assurance";
		PIMEmployeeListPage pim = (PIMEmployeeListPage) homepage.new MenuComponent().clickMenu(menu);
		pim.selectDropDownOptionForField(fieldName,option);
		pim.searchDetails();
		Assert.assertTrue(pim.validateSubUnitDetails(fieldName), "Search results have a unmatching sub unit");
	}
	@Test
	public void _5searchByJobTitleTest() throws InterruptedException {
		fieldName = "Job Title";
		option = "Software Engineer";
		PIMEmployeeListPage pim = (PIMEmployeeListPage) homepage.new MenuComponent().clickMenu(menu);
		pim.selectDropDownOptionForField(fieldName,option);
		pim.searchDetails();
		Assert.assertTrue(pim.validatejobTitleDetails(fieldName), "Search results have a unmatching job title");
	}
	
	@AfterClass
	public void logout() {
		new HomePage().logout();
	}
}

