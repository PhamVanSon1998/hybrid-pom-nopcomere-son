package commons;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AbstractTest {
	private String projectFolder = System.getProperty("user.dir");
	private WebDriver driver;
	private String osName = System.getProperty("os.name");
	// Define log variable
	protected final Log log;

	// Constructor
	protected AbstractTest() {
		// Init log
		log = LogFactory.getLog(getClass());
	}

	protected String firstName, lastName, day, month, year, email, companyName, password, passwordLow, passwordError, confirmPassword, emailNotRegister, emailError;
	protected String country, state, city, address1, address2, zipcode, phoneNumber, faxNumber, newpassword;

	protected WebDriver getBrowserDriver(String browserName, String url, String emailLogin) {
		firstName = "Phan";
		lastName = "Thuy";
		day = "28";
		month = "November";
		year = "1998";
		companyName = "Senda";
		password = "28111998";
		passwordLow = "2811";
		confirmPassword = "281198";
		email = "Phanthuy" + emailLogin + "@gmail.com";
		emailError = "Phanthuy.123";
		passwordError = "123457";
		emailNotRegister = "Phanthuy111@gmail.com";
		country = "Viet Nam";
		state = "Other";
		city = "Nghe An";
		address1 = "Khuong Thuong, Dong da, Ha Noi";
		address2 = "Quynh Lien,Hoang Mai, Nghe An";
		zipcode = "28111998";
		phoneNumber = "037540334";
		faxNumber = "123456";
		newpassword = "281198";
		Browser browser = Browser.valueOf(browserName.toUpperCase());
		if (browser == Browser.FIREFOX_UI) {
			WebDriverManager.firefoxdriver().setup();

			// Disable log
//			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
//			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, projectFolder + File.separator + "browserLog" + File.separator + "FirefoxLog.log");

			// Add Extension
			FirefoxProfile profile = new FirefoxProfile();
			File translate = new File(projectFolder + File.separator + "browserExtensions" + File.separator + "to_google_translate-4.2.0.xpi");
			profile.addExtension(translate);

			// Set language
			FirefoxOptions options = new FirefoxOptions();
			options.setProfile(profile);
			options.addPreference("intl.accept_languages", "vi-vn,vi,en-us,en");

			// Auto Save/Dowload
			options.addPreference("browser.download.folderList", 2);
			options.addPreference("browser.download.dir", projectFolder + File.separator + "dowloadFiles");
			options.addPreference("browser.download.userDownloadDir", true);
			options.addPreference("browser.helperApps.neverAsk.saveToDisk",
					"multipart/x-zip,application/zip,application/x-compressed,application/msword,application/vnd.ms-excel,application/x-excel,application/x-mxexcel,application/octet-stream");
			options.addPreference("pdfjs.disable", true);

			// Incognito
			options.addArguments("-private");
			driver = new FirefoxDriver(options);
		} else if (browser == Browser.CHROME_UI) {
			WebDriverManager.chromedriver().setup();

			// Disable log
			System.setProperty("webdriver.chrome.args", "--disable-logging");
			System.setProperty("webdriver.chrome.silentOutput", "true");

			// Add Extension
			File file = new File(projectFolder + File.separator + "browserExtensions" + File.separator + "extension_2_0_1_0.crx");
			ChromeOptions options = new ChromeOptions();
			options.addExtensions(file);

			// Set language
			options.addArguments("--lang=en");

			// Infor Bar/Notification/Lcation
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-notifications");
			options.addArguments("--disable-geolocation");
			options.setExperimentalOption("useAutomationExtension", false);
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

			// Auto Save/Dowload
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", projectFolder + File.separator + "dowloadFiles");
			options.setExperimentalOption("prefs", chromePrefs);

			// Incognito
			options.addArguments("--incognito");

			driver = new ChromeDriver(options);
		} else if (browser == Browser.FIREFOX_HEADLESS) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.setHeadless(true);
			driver = new FirefoxDriver(options);
		} else if (browser == Browser.CHROME_HEADLESS) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(options);
		} else if (browser == Browser.EDGE_LEGACY) {
			driver = new EdgeDriver();
		} else if (browser == Browser.EDGE_CHROMIUM) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browser == Browser.IE) {
			WebDriverManager.iedriver().arch32().setup();
			driver = new InternetExplorerDriver();
		} else if (browser == Browser.COC_COC) {
			WebDriverManager.chromedriver().driverVersion("104.0.5112.102").setup();
			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:\\Users\\Dell\\AppData\\Local\\CocCoc\\Browser\\Application\\browser.exe");
			driver = new ChromeDriver(options);
		} else if (browser == Browser.OPERA) {
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
		} else {
			throw new RuntimeException("Please input valid browser name value");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(url);
		return driver;
	}

	protected int getRandomNumber() {
		Random random = new Random();
		return random.nextInt(999999);
	}

	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			if (condition == true) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			if (condition == false) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}

	protected void closeBrowserAndDriver() {
		String cmd = "";
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			String driverInstanceName = driver.toString().toLowerCase();
			log.info("Driver instance name = " + driverInstanceName);

			if (driverInstanceName.contains("chrome")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				} else {
					cmd = "pkill chromedriver";
				}
			} else if (driverInstanceName.contains("internetexplorer")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driverInstanceName.contains("firefox")) {
				if (osName.contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				} else {
					cmd = "pkill geckodriver";
				}
			} else if (driverInstanceName.contains("edge")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
				} else {
					cmd = "pkill msedgedriver";
				}
			} else if (driverInstanceName.contains("opera")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
				} else {
					cmd = "pkill operadriver";
				}
			} else if (driverInstanceName.contains("safari")) {
				if (osName.contains("mac")) {
					cmd = "pkill safaridriver";
				}
			}

			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}