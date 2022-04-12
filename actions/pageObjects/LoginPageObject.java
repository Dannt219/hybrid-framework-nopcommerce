package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class LoginPageObject extends BasePage {
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToLoginButton() {
		// TODO Auto-generated method stub
		
	}
	
	public String getErrorMessageAtEmailTextbox() {
		// TODO Auto-generated method stub
		return null;
	}

	public void inputToEmailTextBox(String invalidEmail) {
		// TODO Auto-generated method stub
		
	}

	public String getErrorMessageUnsuccessfull() {
		// TODO Auto-generated method stub
		return null;
	}

	public void inputToPasswordTextbox(String password) {
		// TODO Auto-generated method stub
		
	}



	

}
