package com.qa.tests;

import com.qa.base.Base;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class SampleTest extends Base {
    
    @Test
    public void openGoogle() throws InterruptedException {
        getDriver().get("https://www.google.com");
        getDriver().findElement(By.name("q")).sendKeys("Selenium WebDriver");
        Thread.sleep(5000);
    }
}
