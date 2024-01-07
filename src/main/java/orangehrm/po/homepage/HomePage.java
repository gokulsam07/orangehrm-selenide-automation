package orangehrm.po.homepage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

public class HomePage {
	public WebDriver driver;
	private static final SelenideElement userLoginVerification = $(byAttribute("alt", "profile picture"));

	public HomePage() {
		this.driver = WebDriverRunner.getWebDriver();
	}

	public HomePage isUserLoggedIn() {
		userLoginVerification.shouldBe(visible);
		return this;
	}

	public class MenuComponent {

		public void clickMenu(String menu) {
			driver.findElement(byTagAndText("span", menu)).click();
		}

		public void searchMenu(String searchString) {
			driver.findElement(byAttribute("placeholder", "Search")).sendKeys(searchString);
		}

		public boolean validateSearchResults(String searchString) {
			List<WebElement> ele = driver
					.findElements(byCssSelector(".oxd-text.oxd-text--span.oxd-main-menu-item--name"));
			int count = (int) ele.stream().filter(e -> e.getText().toLowerCase().contains(searchString.toLowerCase()))
					.count();
			return count == ele.size();
		}

		public void searchAndClickMenu(String menu) {
			searchMenu(menu);
			clickMenu(menu);
		}

		public void shrinkMenu() {
			try {
				$(driver.findElement(byCssSelector(".oxd-icon.bi-chevron-left"))).shouldBe(visible).click();
			} catch (NoSuchElementException e) {
				System.out.println("Menu is already closed so opening it");
				$(driver.findElement(byCssSelector(".oxd-icon.bi-chevron-right"))).shouldBe(visible).click();
				$(driver.findElement(byCssSelector(".oxd-icon.bi-chevron-left"))).shouldBe(visible).click();
			}

		}

		public boolean isMenuExpanded() {
			return !driver.findElement(byAttribute("placeholder", "Search")).getAttribute("class").contains("toggled");
		}

		public boolean isMenuShrunk() {
			return driver.findElement(byAttribute("placeholder", "Search")).getAttribute("class").contains("toggled");
		}

		public void expandMenu() {
			try {
				$(driver.findElement(byCssSelector(".oxd-icon.bi-chevron-right"))).shouldBe(visible).click();
			} catch (NoSuchElementException e) {
				System.out.println("Menu is already open so closing it");
				$(driver.findElement(byCssSelector(".oxd-icon.bi-chevron-left"))).shouldBe(visible).click();
				$(driver.findElement(byCssSelector(".oxd-icon.bi-chevron-right"))).shouldBe(visible).click();
			}
		}
	}
}