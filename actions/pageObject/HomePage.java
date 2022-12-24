package pageObject;

import org.openqa.selenium.WebDriver;

import PageUI.HomePageUI;
import commons.AbstractPage;

public class HomePage extends AbstractPage {
	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isRegisterLinkDisplayed() {
		waitToElementVisible(driver, HomePageUI.REGISTER_LINK);
		return isElementDisplayed(driver, HomePageUI.REGISTER_LINK);
	}

	public boolean isLoginLinkDisplayed() {
		waitToElementVisible(driver, HomePageUI.LOGIN_LINK);
		return isElementDisplayed(driver, HomePageUI.LOGIN_LINK);
	}

	public RegisterPage clickToRegisterLink() {
		waitToElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
		return pageObject.PageGenaratorManager.getRegisterPage(driver);
	}


}
