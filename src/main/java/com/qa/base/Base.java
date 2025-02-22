package com.qa.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Properties;

public class Base {
	protected static Properties props;
	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
	protected static ThreadLocal<String> dateTime = new ThreadLocal<String>();

    @BeforeMethod
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            driver.set(new ChromeDriver());
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver.set(new FirefoxDriver());
        } else if (browser.equalsIgnoreCase("edge")) {
            driver.set(new EdgeDriver());
        } else {
            throw new IllegalArgumentException("Invalid browser: " + browser);
        }

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().window().maximize();
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    @AfterMethod
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}
