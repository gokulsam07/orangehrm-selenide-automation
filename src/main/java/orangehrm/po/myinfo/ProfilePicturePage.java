package orangehrm.po.myinfo;

import static com.codeborne.selenide.Selenide.$;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;

import com.codeborne.selenide.SelenideElement;

import orangehrm.po.homepage.ISubMenu;

public class ProfilePicturePage implements ISubMenu {

	private static final SelenideElement addPic = $(".oxd-icon.bi-plus");
	private static final SelenideElement save = $("button[type='submit']");
	private static final SelenideElement success = $(".oxd-toast-content.oxd-toast-content--success");

	

	public void selenideProfielPicUpload(String fileLocation) {
		$("input[type='file']").uploadFromClasspath("headshot-changed.png");
	}
	


	public boolean validateSuccessToast() throws InterruptedException {
		Thread.sleep(2000);
		return success.isDisplayed();
	}
}
