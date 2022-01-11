package com.nopcomercer.user;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;

public class Level_02_Register_Login_BasePage_Part_I {
	WebDriver driver;
	String emailAddress;
	String projectPath = System.getProperty("user.dir");
	
	Select select;
	WebDriverWait explicitWait;
	BasePage basePage;

  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	  driver = new FirefoxDriver();
	  
	  basePage = new BasePage();
	  emailAddress = "";
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
  }
  
  @Test
  public void Register_Empty_Data() {
	  basePage.openUrl(driver, "https://demo.nopcommerce.com/");
	  
	  basePage.clickToElement(driver, "");
	  basePage.clickToElement(driver, "");
  }

  @AfterClass
  public void afterClass() {
  }

}
