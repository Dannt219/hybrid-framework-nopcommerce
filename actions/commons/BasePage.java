package commons;

import org.openqa.selenium.WebDriver;

public class BasePage {
	
	/*Web browser*/
	public void openUrl (WebDriver driver, String url) {
		driver.get(url);
	}
	
	public String getPageTitle (WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getPageUrl (WebDriver driver) {
		return driver.getCurrentUrl();
	}

}
