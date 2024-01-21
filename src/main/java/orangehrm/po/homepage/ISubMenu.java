package orangehrm.po.homepage;

import static com.codeborne.selenide.Selenide.$x;

public interface ISubMenu {

	default boolean validateCurrentPage(String pageName) {
		return $x("//h6[normalize-space()='" + pageName + "']").exists();
	}

}
