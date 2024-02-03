package parallel1.test3.myinfo;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import orangehrm.po.myinfo.MyInfoPage;
import orangehrm.po.myinfo.ProfilePicturePage;

public class _1MyInfoProfileTest extends BaseTest {
	private ProfilePicturePage profilePicPage;
	@Test
	public void _1uploadProfilePictureTest()
			throws IOException, InterruptedException, HeadlessException, UnsupportedFlavorException, AWTException {
		String menu = "My Info";
		String subMenu = "profile picture";
		String file1Loc = "C:\\PersonalWorkspace\\SelenideWorkspace\\selenide-automation\\src\\test\\resources\\headshot-default";
		MyInfoPage myInfoPage = (MyInfoPage) homePage.new MenuComponent().clickMenu(menu);
		if (myInfoPage.validateCurrentMenu(menu)) {
			profilePicPage = (ProfilePicturePage) myInfoPage.clickSubMenu(subMenu);
			profilePicPage.selenideProfielPicUpload(file1Loc);
			profilePicPage.saveChanges();
			Assert.assertTrue(profilePicPage.validateSuccessToast());
		}
	}
}
