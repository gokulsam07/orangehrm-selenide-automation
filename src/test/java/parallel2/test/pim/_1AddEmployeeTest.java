package parallel2.test.pim;

import static com.codeborne.selenide.Selenide.open;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import orangehrm.po.homepage.HomePage;
import orangehrm.po.login.LoginPage;
import orangehrm.po.pim.AddEmployeePage;
import orangehrm.po.pim.PIMEmployeeListPage;

public class _1AddEmployeeTest {
	private HomePage homepage;
	private PIMEmployeeListPage pim;
	private AddEmployeePage addEmp;
	private String menu = "PIM";
	private String subMenu = "Add Employee";
	private String firstName = "Gokul", lastName = "Saminathan", picLocation = "headshot-changed.png";

	@BeforeClass
	public void login() {
		open("/login");
		new LoginPage().enterCredentialsAndSubmit();
		homepage = new HomePage().isUserLoggedIn();
		pim = (PIMEmployeeListPage) homepage.new MenuComponent().clickMenu(menu);
		addEmp = (AddEmployeePage) pim.clickSubMenu(subMenu);
	}

	@Test
	public void _1validateInsufficientDetailsTest() throws InterruptedException {
		addEmp.validateCurrentPage(subMenu);
		addEmp.enterEmployeeDetailsAndSave("", "", "");
		Assert.assertTrue(addEmp.validateRequiredFields(), "Validation error is not highlighted");
	}

	@Test
	public void _2addEmployeeTest() throws InterruptedException {
		addEmp.enterEmployeeDetailsAndSave(firstName, lastName, picLocation);
		Assert.assertTrue(addEmp.validateSuccessToast(), "Employee details is not saved");
	}

	@Test
	public void _3validateAddedEmployeeTest() {
		pim.clickSubMenu("Employee List");
		pim.setEmployeeName(firstName + " " + lastName);
		pim.searchDetails();
		Assert.assertTrue(pim.validateSearchResults(), "Employee details is not visible");
	}

	@Test
	public void _4deleteEmployeeTest() throws InterruptedException {
		Assert.assertTrue(pim.deleteEmployee(), "Employee details is not deleted");
	}

	@Test
	public void _5validatenoRecordsToastTest() throws InterruptedException {
		pim.searchDetails();
		Assert.assertTrue(pim.validateNoRecordToast(), "Record is found");
	}
	@AfterClass
	public void logout() {
		new HomePage().logout();
	}
}
