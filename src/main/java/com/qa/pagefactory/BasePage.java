package com.qa.pagefactory;

import org.openqa.selenium.WebDriver;

import com.qa.action.Action;

public class BasePage extends PageGenerator {
	protected Action action = new Action();

	public BasePage(WebDriver driver) {
		super(driver);
	}
}
