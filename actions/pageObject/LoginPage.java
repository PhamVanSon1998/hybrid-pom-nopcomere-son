package pageObject;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class LoginPage extends AbstractPage {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}


}
