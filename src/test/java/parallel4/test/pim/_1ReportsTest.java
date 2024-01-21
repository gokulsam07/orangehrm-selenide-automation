package parallel4.test.pim;

import static com.codeborne.selenide.Selenide.open;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import orangehrm.po.homepage.HomePage;
import orangehrm.po.login.LoginPage;
import orangehrm.po.pim.PIMEmployeeListPage;
import orangehrm.po.pim.ReportsPage;

public class _1ReportsTest {
	private HomePage homepage;
	private ReportsPage reports;
	private PIMEmployeeListPage pim;
	private String menu = "PIM";
	private String subMenu = "Reports";
	private String currentPage = "Employee Reports";
	private String howTo = "";
	private String reportName ="Selenide Report";
	private String option ="Gender", choice ="Male";
	private String[] displayGroupOption = new String[] {"Personal","Job"}; 
	private String[] displayFieldOption = new String[] {"Employee First Name","Employee Last Name","Job Title","Sub Unit"};
			
	@BeforeClass
	public void login() {
		open("/login");
		new LoginPage().enterCredentialsAndSubmit();
		homepage = new HomePage().isUserLoggedIn();
	}

	@Test
	public void _1validateReportsPageTest() {
		pim = (PIMEmployeeListPage) homepage.new MenuComponent().clickMenu(menu);
		reports = (ReportsPage) pim.clickSubMenu(subMenu);
		reports.validateCurrentPage(currentPage);
	}

	@Test
	public void _2validateReportsCountTest() {
		reports.validateReportsCount();
	}

	@Test
	public void _3validateOrderTest() {
		howTo = "Ascending";
		Assert.assertTrue(reports.validateOrder(howTo), "Values are not in ascending order");
		howTo = "Descending";
		reports.sortReport(howTo);
		Assert.assertTrue(reports.validateOrder(howTo), "Values are not in descending order");
	}
	
	@Test
	public void _4addReportAndValidate(){
		Assert.assertTrue(reports.addReport(reportName,option,choice,displayGroupOption,displayFieldOption),"Report is not saved");
	}
	
	@Test
	public void _5validateSavedReport() throws InterruptedException {
		Assert.assertTrue(reports.validateReport(reportName),"Report is not available");
	}

	@Test
	public void _6deleteReport() throws InterruptedException {
		reports = (ReportsPage) pim.clickSubMenu(subMenu);
		Assert.assertTrue(reports.deletReport(reportName),"Report is not deleted");
	}
	@AfterClass
	public void logout() {
		new HomePage().logout();
	}
}
