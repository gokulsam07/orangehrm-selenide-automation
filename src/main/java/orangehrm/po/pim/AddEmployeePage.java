package orangehrm.po.pim;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.*;
import com.codeborne.selenide.SelenideElement;

import orangehrm.po.homepage.ISubMenu;

public class AddEmployeePage implements ISubMenu {
	private static final SelenideElement picUpload = $("input[type='file']");
	private static final SelenideElement fName = $(".orangehrm-firstname");
	private static final SelenideElement lName = $(".orangehrm-lastname");
	private static final SelenideElement save = $("button[type='submit']");
	private static final SelenideElement success = $(".oxd-toast-content.oxd-toast-content--success");
	private static final SelenideElement validationError = $(byTagAndText("span", "Required"));

	public void enterEmployeeDetailsAndSave(String firstName, String lastName, String picLocation) {
		fName.setValue(firstName);
		lName.setValue(lastName);
		picUpload.uploadFromClasspath(picLocation);
		save.click();
	}

	public boolean validateSuccessToast() throws InterruptedException {
		Thread.sleep(2000);
		return success.isDisplayed();
	}

	public boolean validateRequiredFields() {
		return validationError.isDisplayed();
	}
}
