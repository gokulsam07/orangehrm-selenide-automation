package base;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.screenshot;

import java.io.File;
import java.net.MalformedURLException;

import org.apache.commons.io.FileUtils;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import orangehrm.po.homepage.HomePage;
import orangehrm.po.login.LoginPage;

public class BaseTest {
	protected HomePage homePage;

	@BeforeSuite
	public void cleanUpDir() throws MalformedURLException {
		String screenshotLocation = System.getProperty("user.dir") + File.separator + "build" + File.separator
				+ "reports" + File.separator + "tests";
		try {
			FileUtils.deleteDirectory(new File(screenshotLocation));
		} catch (Exception e) {
			System.out.println("Unable to delete the folder or folder not found");
		}
	}

	@BeforeMethod
	public void spinUpDriver() {
		open("/login");
		new LoginPage().enterCredentialsAndSubmit();
		homePage = new HomePage().isUserLoggedIn();
	}

	@AfterMethod
	public void spinDownDriver(ITestResult result) {
		Reporter.setCurrentTestResult(result);
		if (result.getStatus() == 2) {
			String relativeLoc = result.getMethod().getMethodName();
			screenshot(relativeLoc);
			String ss = System.getProperty("user.dir") + File.separator + "build" + File.separator + "reports"
					+ File.separator + "tests" + File.separator + relativeLoc + ".png";
			Reporter.log("<a href='" + ss + "'><img src='file://" + ss + "' width='822' height='404'/></a>");
		}
		closeWebDriver();
	}

}
