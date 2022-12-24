package pageObject;

import org.openqa.selenium.WebDriver;

import PageUI.RegisterPageUI;
import commons.AbstractPage;

public class RegisterPage extends AbstractPage {
	WebDriver driver;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToRegisterButton() {
		waitToElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver,  RegisterPageUI.REGISTER_BUTTON);
		
	}

	public String verifyFirstNameError() {
		waitToElementVisible(driver, RegisterPageUI.FIRST_ERROR);
		return getElementText(driver, RegisterPageUI.FIRST_ERROR);
	}

	public String verifyLastNameError() {
		waitToElementVisible(driver, RegisterPageUI.LASTNAME_ERROR);
		return getElementText(driver, RegisterPageUI.LASTNAME_ERROR);
	}

	public Object verifyEmailError() {
		waitToElementVisible(driver, RegisterPageUI.EMAIL_ERROR);
		return getElementText(driver, RegisterPageUI.EMAIL_ERROR);
	}

	public boolean verifyPasswordError(String passwordError) {
		waitToElementVisible(driver, RegisterPageUI.PASSWORD_ERROR);
		return getElementText(driver, RegisterPageUI.PASSWORD_ERROR).contains(passwordError);
	}

	public Object verifyConfirmPasswordError() {
		waitToElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR);
		return getElementText(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR);
	}


}
