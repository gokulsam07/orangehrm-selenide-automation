package test.myinfo;

import static com.codeborne.selenide.Selenide.open;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import orangehrm.po.homepage.HomePage;
import orangehrm.po.myinfo.MyInfoPage;
import orangehrm.po.myinfo.ProfilePicturePage;

public class MyInfo_ProfileTest {
	private HomePage homepage;
	private ProfilePicturePage profilePicPage;

	@BeforeMethod
	public void login() {
		open("/login");
		homepage = new HomePage().isUserLoggedIn();
	}

	@Test
	public void uploadProfilePictureTest() throws IOException, InterruptedException, HeadlessException, UnsupportedFlavorException, AWTException {
		String menu = "My Info";
		String subMenu = "profile picture";
		String file1Loc = "C:\\PersonalWorkspace\\SelenideWorkspace\\selenide-automation\\src\\test\\resources\\headshot-default";
		MyInfoPage myInfoPage = (MyInfoPage) homepage.new MenuComponent().clickMenu(menu);
		if (myInfoPage.validateCurrentMenu(menu)) {
			profilePicPage = (ProfilePicturePage) myInfoPage.clickSubMenu(subMenu);
			profilePicPage.robotUpload(file1Loc);
			Assert.assertTrue(profilePicPage.validateSuccessToast());
		}
	}

}
