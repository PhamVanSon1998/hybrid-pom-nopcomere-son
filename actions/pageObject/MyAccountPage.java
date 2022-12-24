package pageObject;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class MyAccountPage extends AbstractPage {
	WebDriver driver;

	public MyAccountPage(WebDriver driver) {
		this.driver = driver;
	}


}
