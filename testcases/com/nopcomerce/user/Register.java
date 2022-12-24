package com.nopcomerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObject.HomePage;
import pageObject.PageGenaratorManager;
import pageObject.RegisterPage;

public class Register extends AbstractTest {

	WebDriver driver;
	HomePage homePageObject;
	RegisterPage registerPageObject;

	@Parameters({ "browser", "url", "emailLogin" })
	@BeforeClass
	public void BeforeClass(String browserName, String urlValues, String emailLogin) {
		driver = getBrowserDriver(browserName, urlValues, emailLogin);
	}

	@Test
	public void TC_01_Register_With_Empty_Data() {
		log.info("RegisterWithEmptyData Step 01: Init HomePage");
		homePageObject = PageGenaratorManager.getHomePage(driver);
		
		log.info("Register With Empty Data-Step 02: Verify Register Link Displayed");
		verifyTrue(homePageObject.isRegisterLinkDisplayed());

		log.info("Register With Empty Data-Step 03: Verify Login Link Displayed");
		verifyTrue(homePageObject.isLoginLinkDisplayed());

		log.info("Register With Empty Data-Step 04: Click to Register Link  and Open Register Page");
		registerPageObject = homePageObject.clickToRegisterLink();
		
		log.info("Register With Empty Data-Step 05: Click to Register Button");
		registerPageObject.clickToRegisterButton();
		
		log.info("Register With Empty Data-Step 06: Verify FirstName error");
		verifyEquals(registerPageObject.verifyFirstNameError(), "First name is required.");
		
		log.info("Register With Empty Data-Step 07: Verify LastName error");
		verifyEquals(registerPageObject.verifyLastNameError(), "Last name is required.");
		
		log.info("Register With Empty Data-Step 08: Verify email error");
		verifyEquals(registerPageObject.verifyEmailError(), "Email is required.");

		log.info("Register With Empty Data-Step 09: Verify password error");
		verifyTrue(registerPageObject.verifyPasswordError( "Password is required."));

		log.info("Register With Empty Data-Step 10: Verify confirm password error");
		verifyEquals(registerPageObject.verifyConfirmPasswordError(), "Password is required.");
	}
}
