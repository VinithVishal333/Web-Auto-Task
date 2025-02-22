package com.qa.action;

import java.time.Duration;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qa.base.BaseTest;

public class Action extends BaseTest {

	public void sendKey(WebElement element, String text, String elementName, int duration) {
		waitForClickabilityOfElement(element, duration);
		clear(element, elementName, duration);
		element.sendKeys(text);
	}

	public void JSClick(WebElement element, String elementName, int i) {
		fluentWait(element, i);
		JavascriptExecutor executor = (JavascriptExecutor) getDriver();
		executor.executeScript("arguments[0].click();", element);
	}

	public void clear(WebElement element, String elementName, int duration) {
		element.clear();
	}

	public void fluentWait(WebElement element, int duration) {
		new FluentWait<WebDriver>((WebDriver) getDriver())
				.withTimeout(Duration.ofSeconds(duration)).pollingEvery(Duration.ofSeconds(2));
	}

	public void waitForVisibiltyOfElement(WebElement element, int duration) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(duration));
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
		}
	}

	public void waitForClickabilityOfElement(WebElement element, int duration) {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(duration));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public String getText(WebElement element, String elementName, int duration) {
			waitForVisibiltyOfElement(element, duration);
			String text = element.getText();
			return text;
	}
	
	public void assertEqualsUsingContains(String ExpectedTxt, String ActualTxt) {
		Assert.assertTrue(StringUtils.containsIgnoreCase(ExpectedTxt, ActualTxt),
				"Error in asserting " + ",Expected : " + ExpectedTxt + ", Actual : " + ActualTxt);
}

}
