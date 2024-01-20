package orangehrm.po.homepage;

import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;


public interface IMenuPage extends ISubMenu {
	
	ISubMenu clickSubMenu(String subMenu);

	default boolean validateCurrentMenu(String menu) {
		SelenideElement currentMenu = $x("//span[text()='"+menu+"']/ancestor::a[contains(@class, 'oxd-main-menu-item')]");
		return currentMenu.attr("class").contains("active");
	}
}
