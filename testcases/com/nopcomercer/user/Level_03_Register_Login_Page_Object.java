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
import pageObjects.RegisterPageObject;

public class Level_03_Register_Login_Page_Object  {


	Select select;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		
		homePage = new HomePageObject(driver);
		registerPage = new RegisterPageObject(driver);

		firstName = "Aidan";
		lastName = "Nguyen";
		emailAddress = "ntd" + generrateFakeNumber() + "@email.vn";
		password = "123456";

	}

	@Test
	public void TC_01_Register_Empty_Data() {
		//openUrl(driver, "https://demo.nopcommerce.com/");
		
		System.out.print("Home Page - Step 01: Click to Register link");
		homePage.clickToRegisterLink();

		System.out.print("Register Page - Step 02: Click to Register button");
		registerPage.clickToRegisterButton();

		
		System.out.print("Register Page - Step 03: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtFirstnameTextbox(),"First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastnameTextbox(),"Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(),"Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(),"Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(),"Password is required.");

	}

	@Test
	public void TC_02_Register_Ivalid_Email() {
	
		System.out.print("Home Page - Step 01: Click to Register link");
		homePage.clickToRegisterLink();

		System.out.print("Register Page - Step 02: Input requiered fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox("123@1234");
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
	

		System.out.print("Register Page - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();

		System.out.print("Register Page - Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorExistingEmailMessage(),"Wrong email");

	}

	@Test
	public void TC_03_Register_Success() {
		System.out.print("Home Page - Step 01: Click to Register link");
		homePage.clickToRegisterLink();

		System.out.print("Register Page - Step 02: Input requiered fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		System.out.print("Register Page - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();


		System.out.print("Register Page - Step 04: Verify success message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");


		System.out.print("Register Page - Step 05: Click to Logout link");
		registerPage.clickToLogoutLink();
	}

	@Test
	public void TC_04_Register_Existing_Email() {
		System.out.print("Home Page - Step 01: Click to Register link");
		homePage.clickToRegisterLink();

		System.out.print("Register Page - Step 02: Input requiered fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		System.out.print("Register Page - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();


		System.out.print("Register Page - Step 04: Verify message error existing email");
		Assert.assertEquals(registerPage.getErrorExistingEmailMessage(),"The specified email already exists");
	

	}

	@Test
	public void TC_05_Register_Password_Less() {
		System.out.print("Home Page - Step 01: Click to Register link");
		homePage.clickToRegisterLink();

		System.out.print("Register Page - Step 02: Input requiered fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox("123");
		registerPage.inputToConfirmPasswordTextbox("123");

		System.out.print("Register Page - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();


		System.out.print("Register Page - Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(),"Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
		System.out.print("Home Page - Step 01: Click to Register link");
		homePage.clickToRegisterLink();

		System.out.print("Register Page - Step 02: Input requiered fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(emailAddress);

		System.out.print("Register Page - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();

		System.out.print("Register Page - Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(),"The password and confirmation password do not match.");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int generrateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(1000);

	}
	private WebDriver driver;
	private String firstName,lastName,emailAddress,password;
	private String projectPath = System.getProperty("user.dir");
	private HomePageObject homePage;
	private RegisterPageObject registerPage;

}
