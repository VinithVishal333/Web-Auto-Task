package com.qa.tests;

import com.qa.base.BaseTest;
import com.qa.pages.SamplePage;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SampleTest2 extends BaseTest {


	@Test(groups = "P1", priority = 1)
	public void open_Google() throws InterruptedException {
		page.GetInstance(SamplePage.class).enterSearchKeywordPage("SearchData").clickSearchBtn();
	System.out.println("Test completed");
	}
}
