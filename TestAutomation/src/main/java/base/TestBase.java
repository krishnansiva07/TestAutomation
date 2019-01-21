package base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import utilities.Constant;
import utilities.Log;
import utilities.TestUtil;

public class TestBase {

	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest logger;

	// public static Logger log;

	public TestBase() throws Exception {
		// TODO Auto-generated constructor stub
		}

	public static void initialization() throws Exception {

		String browserName = TestUtil.getBrowserName();
		System.out.println("Browser Name" + browserName);
		if (browserName.equals(Constant.browserNameChrome)) {
			String path = Constant.currentDir + Constant.driversFolderName
					+ Constant.chromeDriverName;
			System.setProperty("webdriver.chrome.driver", path);
			driver = new ChromeDriver();
			driver.get(TestUtil.getURL());
			TestUtil.maximizeBrowser(driver);
			Log.info("Launching the Chrome Browser");

		} else if (browserName.equals(Constant.browserNamefirefox)) {
			String path = Constant.currentDir + Constant.driversFolderName
					+ Constant.firefoxDriverName;
			System.setProperty("webdriver.chrome.driver", path);
			driver = new FirefoxDriver();
			driver.get(TestUtil.getURL());
			TestUtil.maximizeBrowser(driver);
			Log.info("Launching the firefox Browser");

		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Constant.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Constant.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
	}
}
