package orangehrm.po.myinfo;

import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

import orangehrm.po.homepage.IMenuPage;
import orangehrm.po.homepage.ISubMenu;

public class MyInfoPage implements IMenuPage {
	private static final SelenideElement headshot =$(".employee-image");

	@Override
	public ISubMenu clickSubMenu(String subMenu) {
		if (subMenu.contains("picture")) {
			headshot.click();
			return new ProfilePicturePage();
		} else {
			$(byTagAndText("a", subMenu)).click();
			return switch (subMenu) {
			case "Profile Details" -> new ProfileDetailsPage();
			default -> throw new IllegalArgumentException("menu " + subMenu + "is not valid");
			};
		}
	}

	@Override
	public boolean validateCurrentPage() {
		return false;
	}

}
