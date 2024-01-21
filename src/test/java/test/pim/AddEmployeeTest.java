package test.pim;

import static com.codeborne.selenide.Selenide.open;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import orangehrm.po.homepage.HomePage;
import orangehrm.po.pim.AddEmployeePage;
import orangehrm.po.pim.PIMEmployeeListPage;

public class AddEmployeeTest {
	private HomePage homepage;
	private PIMEmployeeListPage pim;
	private AddEmployeePage addEmp;
	private String menu = "PIM";
	private String subMenu = "Add Employee";
	private String firstName = "Gokul", lastName = "Saminathan", picLocation = "headshot-changed.png";

	@BeforeClass
	public void login() {
		open("/login");
		homepage = new HomePage().isUserLoggedIn();
		pim = (PIMEmployeeListPage) homepage.new MenuComponent().clickMenu(menu);
		addEmp = (AddEmployeePage) pim.clickSubMenu(subMenu);
	}

	@Test(priority = 1)
	public void validateInsufficientDetailsTest() throws InterruptedException {
		addEmp.validateCurrentPage(subMenu);
		addEmp.enterEmployeeDetailsAndSave("", "", "");
		Assert.assertTrue(addEmp.validateRequiredFields(), "Validation error is not highlighted");
	}

	@Test(priority = 2)
	public void addEmployeeTest() throws InterruptedException {
		addEmp.enterEmployeeDetailsAndSave(firstName, lastName, picLocation);
		Assert.assertTrue(addEmp.validateSuccessToast(), "Employee details is not saved");
	}

	@Test(priority = 3)
	public void validateAddedEmployeeTest() {
		pim = (PIMEmployeeListPage)homepage.new MenuComponent().clickMenu(menu);
		pim.setEmployeeName(firstName + " " + lastName);
		pim.searchDetails();
		Assert.assertTrue(pim.validateSearchResults(), "Employee details is not visible");
	}

	@Test(priority = 4)
	public void deleteEmployeeTest() throws InterruptedException {
		Assert.assertTrue(pim.deleteEmployee(), "Employee details is not deleted");
	}

	@Test(priority = 5)
	public void validatenoRecordsToastTest() throws InterruptedException {
		pim.searchDetails();
		Assert.assertTrue(pim.validateNoRecordToast(), "Record is found");
	}
}
