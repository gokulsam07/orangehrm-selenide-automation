package orangehrm.po.pim;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import orangehrm.po.homepage.IMenuPage;
import orangehrm.po.homepage.ISubMenu;

public class PIMEmployeeListPage implements IMenuPage {
	private String searchString = null;
	private static final SelenideElement addEmp = $(byTagAndText("a", "Add Employee"));
	private static final SelenideElement reports = $(byTagAndText("a", "Reports"));
	private static final SelenideElement empList = $(byTagAndText("a", "Employee List"));
	private static final SelenideElement opFields = $(byTagAndText("a", "Optional Fields"));
	private static final SelenideElement errorToast = $(".oxd-toast-content--error");
	private static final SelenideElement successToast = $(".oxd-toast-content.oxd-toast-content--success");
	private static final SelenideElement nextPage = $(".oxd-pagination-page-item .bi-chevron-right");
	private static final SelenideElement prevPage = $(".oxd-pagination-page-item .bi-chevron-left");
	private static final SelenideElement trash = $(
			"button[class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-horizontal-margin']");
	private static final SelenideElement sendToTrash = $(
			"button[class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']");
//Related to employee list
	private static final SelenideElement inpName = $x("//input[contains(@placeholder, 'Type')]");
	private static final SelenideElement search = $("button[type='submit']");
	private static final ElementsCollection fName = $$(".oxd-table-card .oxd-table-cell:nth-child(3) div");
	private static final ElementsCollection lName = $$(".oxd-table-card .oxd-table-cell:nth-child(4) div");
	private static final ElementsCollection jobTitle = $$(".oxd-table-card .oxd-table-cell:nth-child(5) div");
	private static final ElementsCollection subUnit = $$(".oxd-table-card .oxd-table-cell:nth-child(7) div");

	@Override
	public ISubMenu clickSubMenu(String subMenu) {
		if (subMenu.contains("Add Employee")) {
			addEmp.click();
			return new AddEmployeePage();
		} else if (subMenu.contains("Reports")) {
			reports.click();
			return new ReportsPage();
		} else if (subMenu.contains("Employee List")) {
			empList.click();
			return new PIMEmployeeListPage();
		} else {
			$(byTagAndText("a", "Configuration")).click();
			switch (subMenu) {
			case "Optional Fields":
				opFields.click();
				return new OptionalFieldsPage();
			case "Custom Fields":
				opFields.click();
				return new CustomFieldsPage();
			case "Data Import":
				opFields.click();
				return new DataImportPage();
			case "Reporting Methods":
				opFields.click();
				return new ReportingMethodsPage();
			default:
				throw new IllegalArgumentException("menu " + subMenu + " is not valid");
			}
		}
	}

	public void setEmployeeName(String name) {
		if ($(".employee-image").isDisplayed()) {
			empList.click();
		}
		inpName.setValue(name);
		searchString = name;

	}

	public void searchDetails() {
		search.click();
	}

	public boolean validateSearchResultAndSize() {
		int size = 0;
		$(".oxd-table-body").shouldBe(visible);
		size += fName.size();
		while (nextPage.is(visible)) {
			nextPage.click();
			$(".oxd-table-body").shouldBe(visible);
			size += fName.size();
		}
		System.out.println("Actual size : " + size);
		System.out.println("Expected size : " + $(".orangehrm-horizontal-padding .oxd-text").getText());
		return $(".orangehrm-horizontal-padding .oxd-text").getText().contains(Integer.toString(size));
	}

	public boolean validateSearchResults() {
		List<String> fullName = IntStream.range(0, Math.min(fName.texts().size(), lName.texts().size()))
				.mapToObj(i -> fName.texts().get(i) + " " + lName.texts().get(i)).collect(Collectors.toList());
		return fullName.stream().allMatch(i -> i.contains(searchString));
	}

	public boolean validateNoRecordToast() throws InterruptedException {
		Thread.sleep(2000);
		return $(".oxd-toast-content--info .oxd-text--toast-message").text().contains("No Records");
	}

	public boolean validateErrorToast() throws InterruptedException {
		Thread.sleep(2000);
		return errorToast.isDisplayed();
	}

	public void selectDropDownOptionForField(String fieldName, String option) {
		$x("//label[contains(text(),'" + fieldName + "')]/parent::div/following-sibling::div//i").click();
		$(byTagAndText("span", option)).shouldBe(visible).click();
	}

	public boolean validateSubUnitDetails(String unit) {
		return subUnit.texts().stream().allMatch(i -> i.contains(unit));
	}

	public boolean validatejobTitleDetails(String title) {
		return jobTitle.texts().stream().allMatch(i -> i.contains(title));
	}

	public boolean deleteEmployee(String firstName) throws InterruptedException {
		Thread.sleep(5000);
		$x("//div[contains(text(),'"+firstName+"')]/parent::div/following-sibling::div[6]//button[1]").click(ClickOptions.usingJavaScript());
		sendToTrash.click();
		Thread.sleep(2000);
		return successToast.isDisplayed();
	}

}
