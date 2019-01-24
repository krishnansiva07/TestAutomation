package utilities;

import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.usermodel.WorkbookFactory;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.*;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import com.thoughtworks.selenium.webdriven.commands.KeyEvent;

import base.TestBase;

public class TestUtil extends TestBase {

	public TestUtil() throws Exception {
		super();
		action=new Actions(driver);
		// TODO Auto-generated constructor stub
	}
	
	public static String getBrowserName() throws Exception
	{
		ExcelUtils.setExcelFile(Constant.pathTestData+Constant.fileTestData,Constant.sheet2);
		String browserName=ExcelUtils.getCellData(2, 1);
		Log.info("After Reading Browser Name from TestData Excel");
		return browserName;
	}
	
	public static String getURL() throws Exception
	{
		ExcelUtils.setExcelFile(Constant.pathTestData+Constant.fileTestData,Constant.sheet2);
		String homePageURL=ExcelUtils.getCellData(1, 1);
		System.out.println("URL is"+homePageURL);
		return homePageURL;
	}
	
	public static void maximizeBrowser(WebDriver driver)
	{
		driver.manage().window().maximize();
	}

	public static String takeScreenshotAtEndOfTest(String testMethodName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss")
				.format(new Date());
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		System.out.print(currentDir);
		String screenshotPath=Constant.currentDir+Constant.screenshotsFolderName+ testMethodName + dateName +".png";
		FileUtils.copyFile(scrFile, new File(screenshotPath));
		return screenshotPath;
	}

	// Should be called @BeforeTest
	public static void startReport() {
		Log.info("Before Entering Method Start get Property");
		extent = new ExtentReports(System.getProperty("user.dir")
				+ "/test-output/STMExtentReport.html", true);
		Log.info("After Entering Method Start getProperty");
		// extent.addSystemInfo("Environment","Environment Name")
		extent.addSystemInfo("Host Name", "Automation Practice")
				.addSystemInfo("Environment", "AUT")
				.addSystemInfo("User Name", "Siva R");
		Log.info("After Adding System Info");

		extent.loadConfig(new File(System.getProperty("user.dir")
				+ "\\extent-config.xml"));
		Log.info("After loading Config.xml");

	}

	public static void extentStartTest() {
		Log.info("After strting the test");
		logger = extent.startTest("passTest");
	}

	public static void checkStatus(org.testng.ITestResult result) throws IOException {
		if (result.getStatus() == org.testng.ITestResult.FAILURE) {
			String screenshotPath=TestUtil.takeScreenshotAtEndOfTest(result.getName());
			logger.log(LogStatus.FAIL,
					logger.addScreenCapture(screenshotPath));
			logger.log(LogStatus.FAIL,
					"Test Case Failed is " + result.getThrowable());

		} else if (result.getStatus() == org.testng.ITestResult.SKIP) {
			String screenshotPath=TestUtil.takeScreenshotAtEndOfTest(result.getName());
			logger.log(LogStatus.SKIP,
					logger.addScreenCapture(screenshotPath));
		}
		extent.endTest(logger);
	}
	
	public boolean checkElementIsPresent(WebElement element)
	{
		boolean res=element.isEnabled();
		return res;
	}
	public void scrollDown(int noOfKeyDown)
	{
		for(int i=1;i<=noOfKeyDown;i++)
		{
			Log.info("Scrolling Down");
			action.sendKeys(Keys.ARROW_DOWN);
		}
	}
	
	public void performMouseHover(WebElement element)
	{
		Log.info("Mouse Hovering");		
		action.moveToElement(element).build().perform();
	}
	public void performMouseHoverWithClick(WebElement element)
	{
		Log.info("Mouse Hovering");		
		action.moveToElement(element)
			  .click().build().perform();
	}
	
	public void javaScriptExecutorClick(WebElement element,WebDriver driver)
	{
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	//Perform mouse hover on the element by index
	public void moveToListIndex(WebDriver driver,String xpath,int index)
	{
		Log.info("Entering to the movetoListIndex");
		scrollDown(23);
		WebElement ulElement=driver.findElement(By.xpath(xpath));
		List<WebElement> liElements=ulElement.findElements(By.tagName("li"));
		if(checkElementIsPresent(liElements.get(index-1)))
		{
		performMouseHover(liElements.get(index-1));	
		}
	}

	public void clickAddToCartByImageIndex(int index,WebDriver driver)
	{
		//String xpathAddToCartByIndex="//*[@id='homefeatured']/li['+index+']/div/div[2]/div[2]/a[1]";
		String xpathAddToCartByIndex="//*[@id='homefeatured']/li['+index+']/div/div[1]/div/a[1]/img";
		WebElement element = driver.findElement(By.xpath(xpathAddToCartByIndex));
		performMouseHoverWithClick(element);
		
	}
	
	
	
}
