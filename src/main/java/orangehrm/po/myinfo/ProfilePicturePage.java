package orangehrm.po.myinfo;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import com.codeborne.selenide.SelenideElement;

import orangehrm.po.homepage.ISubMenu;

public class ProfilePicturePage implements ISubMenu {

	private static final SelenideElement addPic = $(".oxd-icon.bi-plus");
	private static final SelenideElement save = $("button[type='submit']");
	private static final SelenideElement success = $(".oxd-toast-content.oxd-toast-content--success");

	public static void setClipboardData(String clipboardData) throws HeadlessException, UnsupportedFlavorException, IOException {
		StringSelection stringSelection = new StringSelection(clipboardData);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		System.err.println("CLIPBOARD CONTENTS" + (Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null)).getTransferData(DataFlavor.stringFlavor));
	}

	public void uploadProfilePicture(String fileLocation) throws IOException, InterruptedException {
		try {
//			setClipboardData(fileLocation);
//			addPic.click();
			$("input[type='file']").uploadFromClasspath("headshot-changed.png");
//			Robot robot = new Robot();
//			robot.keyPress(KeyEvent.VK_CONTROL);
//			robot.keyPress(KeyEvent.VK_V);
//			robot.delay(5000);
//			robot.keyRelease(KeyEvent.VK_V);
//			robot.keyRelease(KeyEvent.VK_CONTROL);
//			robot.delay(5000);
//			robot.keyPress(KeyEvent.VK_ENTER);
//			robot.keyRelease(KeyEvent.VK_ENTER);
//			robot.delay(5000);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		Thread.sleep(5000);
		save.click();
	}

	public boolean validateSuccessToast() throws InterruptedException {
		Thread.sleep(2000);
		return success.isDisplayed();
	}

	@Override
	public boolean validateCurrentPage() {
		return $x("//h6[normalize-space()='Change Profile Picture']").exists();
	}
}
