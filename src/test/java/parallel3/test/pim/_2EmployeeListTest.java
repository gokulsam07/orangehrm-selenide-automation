package parallel3.test.pim;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import orangehrm.po.pim.PIMEmployeeListPage;

public class _2EmployeeListTest extends BaseTest {
	private String menu = "PIM";
	private String searchString="";
	private String fieldName = "";
	private String option = "";


	@Test
	public void _1validateEmployeeCountTest() {
		PIMEmployeeListPage pim = (PIMEmployeeListPage) homePage.new MenuComponent().clickMenu(menu);
		pim.validateCurrentMenu(menu);
		Assert.assertTrue(pim.validateSearchResultAndSize(), "Expected size didn't match with actual size");
	}

	@Test
	public void _2validateSearchTest() {
		searchString = "Ad";
		PIMEmployeeListPage pim = (PIMEmployeeListPage) homePage.new MenuComponent().clickMenu(menu);
		pim.setEmployeeName(searchString);
		pim.searchDetails();
		//Failed intentionally
		Assert.assertFalse(pim.validateSearchResults(), "Expected size didn't match with actual size");
	}

	@Test
	public void _3validateNoResultToastTest() throws InterruptedException {
		searchString = "zoo";
		PIMEmployeeListPage pim = (PIMEmployeeListPage) homePage.new MenuComponent().clickMenu(menu);
		pim.setEmployeeName(searchString);
		pim.searchDetails();		
		Assert.assertTrue(pim.validateNoRecordToast(), "Cannot find the No Records Toast");
	}
	@Test
	public void _4searchBySubUnitTest() throws InterruptedException {
		fieldName = "Sub Unit";
		option = "Quality Assurance";
		PIMEmployeeListPage pim = (PIMEmployeeListPage) homePage.new MenuComponent().clickMenu(menu);
		pim.selectDropDownOptionForField(fieldName,option);
		pim.searchDetails();
		Assert.fail(); //Failed intentionally
		Assert.assertTrue(pim.validateSubUnitDetails(fieldName), "Search results have a unmatching sub unit");
	}
	@Test
	public void _5searchByJobTitleTest() throws InterruptedException {
		fieldName = "Job Title";
		option = "Software Engineer";
		PIMEmployeeListPage pim = (PIMEmployeeListPage) homePage.new MenuComponent().clickMenu(menu);
		pim.selectDropDownOptionForField(fieldName,option);
		pim.searchDetails();
		Assert.assertTrue(pim.validatejobTitleDetails(fieldName), "Search results have a unmatching job title");
	}
}

