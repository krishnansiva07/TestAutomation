package utilities;

import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.*;

import org.apache.commons.io.FileUtils;
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.usermodel.WorkbookFactory;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.*;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import base.TestBase;

public class TestUtil extends TestBase {

	public TestUtil() throws Exception {
		super();
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

	public static void checkStatus(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshotPath=TestUtil.takeScreenshotAtEndOfTest(result.getName());
			logger.log(LogStatus.FAIL,
					logger.addScreenCapture(screenshotPath));
			logger.log(LogStatus.FAIL,
					"Test Case Failed is " + result.getThrowable());

		} else if (result.getStatus() == ITestResult.SKIP) {
			String screenshotPath=TestUtil.takeScreenshotAtEndOfTest(result.getName());
			logger.log(LogStatus.SKIP,
					logger.addScreenCapture(screenshotPath));
		}
		extent.endTest(logger);
	}

}
