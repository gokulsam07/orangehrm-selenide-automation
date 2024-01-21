package orangehrm.po.pim;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import orangehrm.po.homepage.ISubMenu;

public class ReportsPage implements ISubMenu {
	private static final ElementsCollection reportList = $$(".oxd-table-card");
	private static final SelenideElement displayCount = $(
			"div[class='orangehrm-horizontal-padding orangehrm-vertical-padding'] span[class='oxd-text oxd-text--span']");
	private static final SelenideElement sort = $(".oxd-table-header-sort");
	private static final SelenideElement add = $(".bi-plus");
	private static final SelenideElement reportNameInput = $("input[placeholder='Type here ...']");
	private static final SelenideElement save = $("button[type='submit']");
	private static final SelenideElement successToast = $(".oxd-toast-content.oxd-toast-content--success");
	private static final SelenideElement sendToTrash = $(
			"button[class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']");

	public boolean validateReportsCount() {
		return displayCount.text().contains(Integer.toString(reportList.size()));
	}

	public void sortReport(String howTo) {
		sort.click();
		$x("//span[normalize-space()='" + howTo + "']").click();
	}

	public boolean validateOrder(String howTo) {
		String sortClass = $(".oxd-table-header-sort i").attr("class");
		if (howTo.equals("Ascending")) {
			if (sortClass.contains("down")) {
				return isAscendingOrder(reportList.texts());
			} else {
				sortReport(howTo);
				return isAscendingOrder(reportList.texts());
			}

		} else if (howTo.equals("Descending")) {
			if (sortClass.contains("up")) {
				return isDescendingOrder(reportList.texts());
			} else {
				sortReport(howTo);
				return isDescendingOrder(reportList.texts());
			}
		}
		return false;
	}

	private boolean isAscendingOrder(List<String> list) {
		List<String> list2 = list.stream().sorted().collect(Collectors.toList());
		return list.equals(list2);
	}

	private boolean isDescendingOrder(List<String> list) {
		List<String> list2 = list.stream().sorted(Comparator.reverseOrder()).toList();
		return list.equals(list2);
	}

	public boolean addReport(String reportName, String option, String choice, String displayGroupOption[],
			String displayFieldOption[]) {
		add.click();
		reportNameInput.shouldBe(visible,Duration.ofSeconds(5)).setValue(reportName);
			addSelectionCriteria(option, choice);
			int j = 0;
			for (int i = 0; i < displayGroupOption.length; i++) {
				addDisplayFieldGroup(displayGroupOption[i]);
				if (i < 1) {
					while (j < displayGroupOption.length) {
						addDisplayFields(displayFieldOption[j]);
						j++;
					}
				}
				if (i >=1) {
					while (j < displayFieldOption.length) {
						addDisplayFields(displayFieldOption[j]);
						j++;
					}
				}
		}
			enableHeaders();
			save.click();
			sleep(2000);
		return successToast.isDisplayed();
	}

	private void addSelectionCriteria(String option, String choice) {
		$x("//label[contains(text(),'Selection Criteria')]/parent::div/following-sibling::div//i").click();
		$(byTagAndText("span", option)).shouldBe(visible).click();
		$x("//label[contains(text(),'Selection Criteria')]/parent::div/parent::div/following-sibling::div//i").click();

		// Below lines of code is to select options for each dropdown
		$x("//label[contains(text(),'" + option + "')]/parent::div/following-sibling::div//i").click();
		$(byTagAndText("span", choice)).shouldBe(visible).click();
	}

	private void addDisplayFieldGroup(String displayGroupOption){
		$x("//label[contains(text(),'Display Field Group')]/parent::div/following-sibling::div//i").shouldBe(interactable,Duration.ofSeconds(5)).click();
		$(byTagAndText("span", displayGroupOption)).shouldBe(visible).click();
	}

	private void addDisplayFields(String displayFieldOption) {
		$x("//label[(text()='Select Display Field')]/parent::div/following-sibling::div//i").shouldBe(interactable,Duration.ofSeconds(5)).click();
		$(byTagAndText("span", displayFieldOption)).shouldBe(visible).click();
		/* below code is to add the fields */
		$x("//label[contains(text(),'Display Field')]/parent::div/parent::div/following-sibling::div//i").shouldBe(interactable,Duration.ofSeconds(5)).click();
	}

	private void enableHeaders() {
		ElementsCollection toggles = $$(".oxd-switch-input");
		for (SelenideElement toggle : toggles) {
			toggle.click();
		}
	}

	public boolean validateReport(String reportName) throws InterruptedException {
		Thread.sleep(5000);
		return $x("//h6[normalize-space()='"+reportName+"']").exists() ;
	}

	public boolean deletReport(String reportName) throws InterruptedException {
		$x("//div[text()='"+reportName+"']/parent::div/following-sibling::div//button[1]").click();
		sendToTrash.click();
		Thread.sleep(2000);
		return successToast.isDisplayed();
	}

}
