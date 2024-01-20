package orangehrm.po.myinfo;
import static com.codeborne.selenide.Selenide.*;
import orangehrm.po.homepage.ISubMenu;

public class ProfileDetailsPage implements ISubMenu {

	@Override
	public boolean validateCurrentPage() {
		return $x("//h6[normalize-space()='Personal Details']").exists();
	}

}
