package com.qa.pages;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.qa.pagefactory.BasePage;

public class RedirectingPage extends BasePage {

	public RedirectingPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//button[translate(normalize-space(text()), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='select a photo']")
	private WebElement selectAPhotoButton;

	public void toAssertWBHMClosetHeading(String WbhmMyClosetHeadingTxt) {
		action.assertEqualsUsingContains(WbhmMyClosetHeadingTxt,
				action.getText(selectAPhotoButton, "wbhm My Closet Heading", 10));
	}
	
	


}
