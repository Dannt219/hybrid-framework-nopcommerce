package com.nopcomercer.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class Level_03_Page_Object_02_Login  {
	
	private WebDriver driver;
	private String firstName,lastName,invalidEmail, notFoundEmail,existingEmail,password;
	private String projectPath = System.getProperty("user.dir");
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	Select select;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		homePage = new HomePageObject(driver);
		driver.get("https://demo.nopcommerce.com/");

		firstName = "Aidan";
		lastName = "Nguyen";
		existingEmail = "ntd" + generrateFakeNumber() + "@email.vn";
		password = "123456";
		invalidEmail = "dannt@dannt.com@.vn";
		notFoundEmail = "ntd" + generrateFakeNumber() + "@email.com";
		
		System.out.print("Pre-Condition - Step 01: Click to Register link");
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);

		System.out.print("Pre-Condition - Step 02: Input requiered fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(existingEmail);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		System.out.print("Pre-Condition - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();


		System.out.print("Pre-Condition - Step 04: Verify success message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");


		System.out.print("Pre-Condition - Step 05: Click to Logout link");
		registerPage.clickToLogoutLink();
		
		// click logout thi business no se ve trang home
		homePage = new HomePageObject(driver);

	}

	@Test
	public void Login_01_Empty_Data() {
		homePage.clickToLoginLink();
		
		// Tu trang Home Click Login khoi tao Login
		loginPage = new LoginPageObject(driver);
		
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
		
	}

	@Test
	public void Login_02_Invalid_Email() {
		homePage.clickToLoginLink();
		
		// Tu trang Home Click Login khoi tao Login
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTextBox(invalidEmail);
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");
		
	}

	@Test
	public void Login_03_Email_Not_Found() {
		homePage.clickToLoginLink();
		
		// Tu trang Home Click Login khoi tao Login
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTextBox(notFoundEmail);
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getErrorMessageUnsuccessfull(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
		
	}

	@Test
	public void Login_04_Existing_Email_Empty_Password() {
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTextBox(existingEmail);
		loginPage.inputToPasswordTextbox("");
		
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageUnsuccessfull(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrec");

	}

	@Test
	public void Login_05_Existing_Email_Incorrect_Password() {
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTextBox(existingEmail);
		loginPage.inputToPasswordTextbox("9865345");
	
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageUnsuccessfull(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrec");

	}
	
	@Test
	public void Login_06_Valid_Email_Password() {
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTextBox(existingEmail);
		loginPage.inputToPasswordTextbox(password);
		
		loginPage.clickToLoginButton();
		//Login thanh con se quay ve Homepage
		homePage = new HomePageObject(driver);
		
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int generrateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(1000);

	}


}
