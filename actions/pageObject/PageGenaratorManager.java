package pageObject;

import org.openqa.selenium.WebDriver;

public class PageGenaratorManager {
	
	public static HomePage getHomePage (WebDriver driver) {
		return new HomePage (driver);
	}

	public static RegisterPage getRegisterPage(WebDriver driver) {
		return new RegisterPage(driver);
	}
	
	public static LoginPage getLoginPage(WebDriver driver) {
		return new LoginPage(driver);
	}
	
	public static MyAccountPage getMyAccountPage(WebDriver driver) {
		return new MyAccountPage(driver);
	}
	
	public static SearchAdvanceSearchPage getSearchAdvanceSearchPage(WebDriver driver) {
		return new SearchAdvanceSearchPage(driver);
	}
	
	public static SortDisplayPagingPage getSortDisplayPagingPage(WebDriver driver) {
		return new SortDisplayPagingPage(driver);
	}
	
	public static WishlistCompareRecentViewPage getWishlistCompareRecentViewPage(WebDriver driver) {
		return new WishlistCompareRecentViewPage(driver);
	}
	
	public static OrderPage getOrderPage(WebDriver driver) {
		return new OrderPage(driver);
	}
	
}
