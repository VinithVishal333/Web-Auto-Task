
package com.qa.base;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.qa.pagefactory.PageGenerator;

public class BaseTest {
	protected static Properties props;
	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
	protected static ThreadLocal<String> dateTime = new ThreadLocal<String>();
	public static String headLessMode;
	public PageGenerator page;
	String runMode;
	String headMode;
	URL url;

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static Properties getProps() {
		return props;
	}



	@Parameters({ "browser", "className" })
	@BeforeTest(alwaysRun = true)
	public void beforeTest(@Optional String browser, @Optional String className) throws Exception {
		browser = System.getProperty("browser", browser);
		if (browser == null)
			browser = "CHROME";
		if (className == null)
			browser = "Test";

		InputStream configis = null;

		try {
			props = new Properties();
			String propFileName = "config.properties";

			configis = getClass().getClassLoader().getResourceAsStream(propFileName);
			props.load(configis);
		} catch (Exception e) {
			System.out.println("Config initialization failure. ABORT!!!\n" + e.toString());
			throw e;
		} finally {
			if (configis != null) {
				configis.close();
			}
		}

	}



	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	public void launchDriver(@Optional String browser, ITestContext context) throws Exception {
		if (browser == null)
			browser = "CHROME";
		runMode = props.getProperty("runMode");
		headMode = props.getProperty("headMode");
		browser = System.getProperty("browser", browser);

		if (runMode == null || runMode.trim().equals("")
				|| (!runMode.trim().toLowerCase().equals("local") && !runMode.trim().toLowerCase().equals("docker"))) {
			System.out.println("Run Mode is not defined in the config.properties");
			System.out.println("Please choose run mode as Local or Docker");
			System.exit(0);
		}

		if (browser.equalsIgnoreCase("Chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--window-size=1920,1080");
			if (headMode != null && headMode.equalsIgnoreCase("True"))
				options.addArguments("--headless");
			if (runMode.equalsIgnoreCase("Docker")) {
				options.addArguments("--no-sandbox");
				options.addArguments("--ignore-ssl-errors=yes");
				options.addArguments("--ignore-certificate-errors");
				URL url = URI.create("http://localhost:4444").toURL();
				driver.set(new RemoteWebDriver(url, options));
			} else if (runMode.equalsIgnoreCase("Local")) {
				driver.set(new ChromeDriver(options));
			}
		} else if (browser.equalsIgnoreCase("FireFox")) {
			driver.set(new FirefoxDriver());
		} else if (browser.equalsIgnoreCase("Edge")) {
			driver.set(new EdgeDriver());
		} else {
			throw new IllegalArgumentException("Invalid browser: " + browser);
		}
		// Maximize the screen
		getDriver().manage().window().maximize();
		// Delete all the cookies
		getDriver().manage().deleteAllCookies();
		// Implicit TimeOuts
		getDriver().manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(Integer.parseInt(props.getProperty("implicitWait"))));
		// Launching the URL
		getDriver().get(props.getProperty("url"));
		page = new PageGenerator(getDriver());
	}

	@AfterMethod(alwaysRun = true)
	public synchronized void quitDriver() {
		getDriver().quit();
	}

}
