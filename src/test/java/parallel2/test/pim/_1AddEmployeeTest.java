package parallel2.test.pim;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import orangehrm.po.pim.AddEmployeePage;
import orangehrm.po.pim.PIMEmployeeListPage;

public class _1AddEmployeeTest extends BaseTest {
	private PIMEmployeeListPage pim;
	private AddEmployeePage addEmp;
	private String menu = "PIM";
	private String subMenu = "Add Employee";
	private String firstName = "Gokul", lastName = "Saminathan", picLocation = "headshot-changed.png";

	@Test
	public void _1validateInsufficientDetailsTest() throws InterruptedException {
		pim = (PIMEmployeeListPage) homePage.new MenuComponent().clickMenu(menu);
		addEmp = (AddEmployeePage) pim.clickSubMenu(subMenu);
		addEmp.validateCurrentPage(subMenu);
		addEmp.enterEmployeeDetailsAndSave("", "", "");
		Assert.assertTrue(addEmp.validateRequiredFields(), "Validation error is not highlighted");
	}

	@Test
	public void _2addEmployeeTest() throws InterruptedException {
		pim = (PIMEmployeeListPage) homePage.new MenuComponent().clickMenu(menu);
		addEmp = (AddEmployeePage) pim.clickSubMenu(subMenu);
		addEmp.enterEmployeeDetailsAndSave(firstName, lastName, picLocation);
		Assert.assertTrue(addEmp.validateSuccessToast(), "Employee details is not saved");
	}

	@Test
	public void _3validateAddedEmployeeTest() {
		pim = (PIMEmployeeListPage) homePage.new MenuComponent().clickMenu(menu);
		addEmp = (AddEmployeePage) pim.clickSubMenu(subMenu);
		pim.clickSubMenu("Employee List");
		pim.setEmployeeName(firstName + " " + lastName);
		pim.searchDetails();
		Assert.assertTrue(pim.validateSearchResults(), "Employee details is not visible");
	}

	@Test
	public void _4deleteEmployeeTest() throws InterruptedException {
		pim = (PIMEmployeeListPage) homePage.new MenuComponent().clickMenu(menu);
		pim.setEmployeeName(firstName);
		pim.searchDetails();
		Assert.assertTrue(pim.deleteEmployee(firstName), "Employee details is not deleted");
	}

	@Test
	public void _5validatenoRecordsToastTest() throws InterruptedException {
		pim = (PIMEmployeeListPage) homePage.new MenuComponent().clickMenu(menu);
		pim.setEmployeeName(firstName);
		pim.searchDetails();
		Assert.assertTrue(pim.validateNoRecordToast(), "Record is found");
	}
}
