package orangehrm.po.login;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.SelenideElement;

import orangehrm.po.homepage.HomePage;
public class LoginPage {
	private static final SelenideElement username = $(byName("username"));
	private static final SelenideElement password = $(byName("password"));
	private static final SelenideElement submit = $(byTagAndText("button","Login"));
	
	public HomePage enterCredentialsAndSubmit() {
		username.shouldBe(visible).setValue("Admin");
		password.shouldBe(visible).setValue("admin123");
		submit.shouldBe(visible).click();
		return new HomePage();
	}
}
