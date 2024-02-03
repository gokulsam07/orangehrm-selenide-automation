package parallel4.test.pim;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import orangehrm.po.pim.PIMEmployeeListPage;
import orangehrm.po.pim.ReportsPage;

public class _1ReportsTest extends BaseTest {

	private ReportsPage reports;
	private PIMEmployeeListPage pim;
	private String menu = "PIM";
	private String subMenu = "Reports";
	private String currentPage = "Employee Reports";
	private String howTo = "";
	private String reportName = "Selenide Report";
	private String option = "Gender", choice = "Male";
	private String[] displayGroupOption = new String[] { "Personal", "Job" };
	private String[] displayFieldOption = new String[] { "Employee First Name", "Employee Last Name", "Job Title",
			"Sub Unit" };

	@Test
	public void _1validateReportsPageTest() {
		pim = (PIMEmployeeListPage) homePage.new MenuComponent().clickMenu(menu);
		reports = (ReportsPage) pim.clickSubMenu(subMenu);
		reports.validateCurrentPage(currentPage);
	}

	@Test
	public void _2validateReportsCountTest() {
		pim = (PIMEmployeeListPage) homePage.new MenuComponent().clickMenu(menu);
		reports = (ReportsPage) pim.clickSubMenu(subMenu);
		Assert.assertTrue(reports.validateReportsCount(), "Report count doesn't match");
	}

	@Test
	public void _3validateOrderTest() {
		pim = (PIMEmployeeListPage) homePage.new MenuComponent().clickMenu(menu);
		reports = (ReportsPage) pim.clickSubMenu(subMenu);
		howTo = "Ascending";
		Assert.assertTrue(reports.validateOrder(howTo), "Values are not in ascending order");
		howTo = "Descending";
		reports.sortReport(howTo);
		Assert.assertTrue(reports.validateOrder(howTo), "Values are not in descending order");
	}

	@Test
	public void _4addReportAndValidate() {
		pim = (PIMEmployeeListPage) homePage.new MenuComponent().clickMenu(menu);
		reports = (ReportsPage) pim.clickSubMenu(subMenu);
		Assert.assertTrue(reports.addReport(reportName, option, choice, displayGroupOption, displayFieldOption),
				"Report is not saved");
	}

	@Test
	public void _5validateSavedReport() throws InterruptedException {
		pim = (PIMEmployeeListPage) homePage.new MenuComponent().clickMenu(menu);
		reports = (ReportsPage) pim.clickSubMenu(subMenu);
		Assert.assertTrue(reports.validateReport(reportName), "Report is not available");
	}

	@Test
	public void _6deleteReport() throws InterruptedException {
		pim = (PIMEmployeeListPage) homePage.new MenuComponent().clickMenu(menu);
		reports = (ReportsPage) pim.clickSubMenu(subMenu);
		Assert.assertTrue(reports.deletReport(reportName),"Report is not deleted");
	}
}
