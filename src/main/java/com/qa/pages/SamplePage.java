package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.pagefactory.BasePage;

public class SamplePage extends BasePage {

	public SamplePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "(//input[@aria-label='Google Search'])[last()]")
	private WebElement searchBtn;
	@FindBy(name = "q")
	private WebElement searchInputField;
	
	
	public SamplePage enterSearchKeywordPage(String text) {
		action.sendKey(searchInputField, text, "Search Input Field", 5);
		return this;
	}
	
	public SamplePage clickSearchBtn() {
		action.JSClick(searchBtn, "Search Button", 5);
		return PageFactory.initElements(driver, SamplePage.class);
	}
	

}
