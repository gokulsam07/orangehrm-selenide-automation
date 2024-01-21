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

	public static void setClipboardData(String clipboardData)
			throws HeadlessException, UnsupportedFlavorException, IOException {
		StringSelection stringSelection = new StringSelection(clipboardData);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		System.out.println("CLIPBOARD CONTENTS :" + (Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null))
				.getTransferData(DataFlavor.stringFlavor));
	}

	public void selenideProfielPicUpload(String fileLocation) {
		$("input[type='file']").uploadFromClasspath("headshot-changed.png");

	}

	public void robotUpload(String fileLocation)
			throws HeadlessException, UnsupportedFlavorException, IOException, AWTException {
		setClipboardData(fileLocation);
		addPic.click();
		Robot robot = new Robot();
		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_V);
		robot.delay(1000);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(1000);
		save.click();
	}

	public boolean validateSuccessToast() throws InterruptedException {
		Thread.sleep(2000);
		return success.isDisplayed();
	}
}
