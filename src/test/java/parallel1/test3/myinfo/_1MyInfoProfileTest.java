package parallel1.test3.myinfo;

import static com.codeborne.selenide.Selenide.open;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import orangehrm.po.homepage.HomePage;
import orangehrm.po.login.LoginPage;
import orangehrm.po.myinfo.MyInfoPage;
import orangehrm.po.myinfo.ProfilePicturePage;

public class _1MyInfoProfileTest {
	private HomePage homepage;
	private ProfilePicturePage profilePicPage;

	@BeforeClass
	public void login() {
		open("/login");
		new LoginPage().enterCredentialsAndSubmit();
		homepage = new HomePage().isUserLoggedIn();
	}

	@Test
	public void _1uploadProfilePictureTest()
			throws IOException, InterruptedException, HeadlessException, UnsupportedFlavorException, AWTException {
		String menu = "My Info";
		String subMenu = "profile picture";
		String file1Loc = "C:\\PersonalWorkspace\\SelenideWorkspace\\selenide-automation\\src\\test\\resources\\headshot-default";
		MyInfoPage myInfoPage = (MyInfoPage) homepage.new MenuComponent().clickMenu(menu);
		if (myInfoPage.validateCurrentMenu(menu)) {
			profilePicPage = (ProfilePicturePage) myInfoPage.clickSubMenu(subMenu);
			profilePicPage.selenideProfielPicUpload(file1Loc);
			Assert.assertTrue(profilePicPage.validateSuccessToast());
		}
	}
	@AfterClass
	public void logout() {
		new HomePage().logout();
	}
}
