package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	
	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}
	
	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}
	
	public String getAlerText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}
	
	public void sendKeyToAlert(WebDriver driver, String value) {
		waitForAlertPresence(driver).sendKeys(value);
	}
	
    public void switchToWindowbyID(WebDriver driver, String parentID) {
		
		Set<String> allTabIDs = driver.getWindowHandles();
		
		for (String id : allTabIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
			}
		}		
	}
	
	public void switchToWindowTitle(WebDriver driver, String titleTab) {
		Set<String> allTabsID = driver.getWindowHandles();
		
		for (String id : allTabsID) {
			driver.switchTo().window(id);
			String getTitle = driver.getTitle();
			if(getTitle.equals(titleTab))
				break;
		}
	}

	public void closeTabsexceptParrent(WebDriver driver, String parentTab) {
		
		Set<String> allTabIDs = driver.getWindowHandles();
		
		for (String id : allTabIDs) {
			if(!id.equals(parentTab)) {
				driver.switchTo().window(id);
				driver.close();
				
			}
		}
		driver.switchTo().window(parentTab);
	}
	
	public void SleepInSecond(long timeoutSecond) {
		try {
			Thread.sleep(timeoutSecond*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	/*Web element*/
	public By getByXpath(String xpathExpression) {
		return By.xpath(xpathExpression);
	}
	
	public WebElement getWebElement(WebDriver driver, String xpathExpression) {
		return driver.findElement(getByXpath(xpathExpression));
	}
	
	public List<WebElement> getWebElements(WebDriver driver, String xpathExpression) {
		return driver.findElements(getByXpath(xpathExpression));
	}
	
	public void clickToElement(WebDriver driver, String xpathExpression) {
		getWebElement(driver, xpathExpression).click();
	}
	
	public void senkeyToElement(WebDriver driver,  String xpathExpression, String value) {
		getWebElement(driver, xpathExpression).clear();
		getWebElement(driver, xpathExpression).sendKeys(value);
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String xpathExpression, String itemText) {
		new Select(getWebElement(driver, xpathExpression)).selectByVisibleText(itemText);
	}
	
	public String getSelectTextInDefaultDropdown(WebDriver driver, String xpathExpression) {
		return new Select(getWebElement(driver, xpathExpression)).getFirstSelectedOption().getText();
	}
	
	public boolean isDefaultDropdownMutiple(WebDriver driver, String xpathExpression) {
		 return new Select(getWebElement(driver, xpathExpression)).isMultiple();
	}
	
	public void selectItemInCustomDropdown(WebDriver driver, String parentxpathExpression, String childItemxpathExpression, String expectedItem) {
		getWebElement(driver, parentxpathExpression).click();
		SleepInSecond(1);

		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemxpathExpression)));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				SleepInSecond(1);

				item.click();
				SleepInSecond(1);
				break;
			}
		}
	}
	
	public String getElementText(WebDriver driver, String xpathExpression) {
		return getWebElement(driver, xpathExpression).getText();
	}
	
	public String getElementAtribute(WebDriver driver, String xpathExpression, String attributeName) {
		return getWebElement(driver, xpathExpression).getAttribute(attributeName);
	}
	
	public String getElementCssValue(WebDriver driver, String xpathExpression, String propertyName) {
	    return getWebElement(driver, xpathExpression).getCssValue(propertyName);
	}
	
	public String getHexaColorByRgbaColor(String rgbaColor) {
		return Color.fromString(rgbaColor).asHex();
	}

	public int getElementNumber(WebDriver driver, String xpathExpression) {
		return getWebElements(driver, xpathExpression).size();
	}
	
	public void checkToRadioOrCheckbox(WebDriver driver, String xpathExpression) {
		if (!getWebElement(driver, xpathExpression).isSelected()) {
			getWebElement(driver, xpathExpression).click();
		}
	}
	
	public void unCheckToCheckbox(WebDriver driver, String xpathExpression) {
		if(getWebElement(driver, xpathExpression).isSelected()) {
			getWebElement(driver, xpathExpression).click();
		}
	}
	
	public Boolean isElementDisplayed(WebDriver driver, String xpathExpression) {
		return getWebElement(driver, xpathExpression).isDisplayed();
	}
	
	public  boolean isElementEnabled(WebDriver driver, String xpathExpression) {
		return getWebElement(driver, xpathExpression).isEnabled();
	}
	
	public boolean isElementSelected(WebDriver driver, String xpathExpression) {
		return getWebElement(driver, xpathExpression).isSelected();
	}

	public void switchToFrame(WebDriver driver, String xpathExpression) {
		driver.switchTo().frame(getWebElement(driver, xpathExpression));
	}
	
	public void switchToDefaultContentPage(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public void hoverMouseToElement(WebDriver driver, String xpathExpression) {
		new Actions(driver).moveToElement(getWebElement(driver, xpathExpression)).perform();
	}
	
	public void pressKeyboardToElement(WebDriver driver, String xpathExpression, Keys key) {
		new Actions(driver).sendKeys(getWebElement(driver, xpathExpression), key).perform();
	}

	public Object executeForBrowser(WebDriver driver, String javaScript) {
		return ((JavascriptExecutor) driver).executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		String textActual = (String) ((JavascriptExecutor) driver)
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		((JavascriptExecutor) driver).executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String xpathExpression) {
		WebElement element = getWebElement(driver, xpathExpression);
		String originalStyle = element.getAttribute("style");
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		SleepInSecond(1);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String xpathExpression) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, xpathExpression));
	}

	public void scrollToElement(WebDriver driver, String xpathExpression) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpathExpression));
	}

	public void sendkeyToElementByJS(WebDriver driver, String xpathExpression, String value) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, xpathExpression));
	}

	public void removeAttributeInDOM(WebDriver driver, String xpathExpression, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, xpathExpression));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String xpathExpression) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, xpathExpression));
	}

	public boolean isImageLoaded(WebDriver driver, String xpathExpression) {
		boolean status = (boolean) ((JavascriptExecutor) driver).executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getWebElement(driver, xpathExpression));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	
	public void waitForElementVisible(WebDriver driver, String xpathExpression) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathExpression)));
	}
	
	public void waitForElementClickable(WebDriver driver, String xpathExpression) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.elementToBeClickable(getByXpath(xpathExpression)));
	}

	public void waitForElementInvisible(WebDriver driver, String xpathExpression) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathExpression)));
	}
	
	public void waitForAllElementVisible(WebDriver driver, String xpathExpression) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathExpression)));
	}
	public void waitForAllElementInvisible(WebDriver driver, String xpathExpression) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfAllElements(getWebElements(driver, xpathExpression)));
	}
	
//	private long shortTimeout = 5;
	private long longTimeout = 30;
	
}
