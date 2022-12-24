package pageObject;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class OrderPage extends AbstractPage {
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		this.driver = driver;
	}


}
