package utilities;

import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	
	public void setTestData(String Path,String fileNamw) throws Exception
	{
		ExcelUtils.setExcelFile(Constant.pathTestData+Constant.fileTestData,Constant.sheet2);
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
		logger = extent.startTest("Checkout as Guest User");
	}

	//This method should be called after method
	public static void checkStatus(org.testng.ITestResult result) throws IOException {
		if(result.getStatus() == org.testng.ITestResult.SUCCESS){
			logger.log(LogStatus.PASS,result.getName());
		}
		else if (result.getStatus() == org.testng.ITestResult.FAILURE) {
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
	
	public WebElement findElementByXpath(String xpath)
	{
		element = driver.findElement(By.xpath(xpath));
		return element;
	}
	
	public boolean checkElementIsPresent(WebElement element)
	{
		boolean res=element.isEnabled();
		return res;
	}
	
	public void performMouseHover(WebElement element)
	{
		Log.info("Mouse Hovering without click");		
		action.moveToElement(element).build().perform();
	}
	public void performMouseHoverWithClick(WebElement element)
	{
		Log.info("Mouse Hovering with click");		
		action.moveToElement(element)
			  .click().build().perform();
	}
	
	public void javaScriptExecutorClick(WebElement element,WebDriver driver)
	{
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	//Perform scroll down
	public void scrollDown()
	{
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0,1000)");

	}
	
	public List<String> clickAddToCartByImageIndexQuickView(int index,WebDriver driver)
	{
		//scrollDown();
		element=findElementByXpath(Constant.xpathOfItemByIndex);
		performMouseHover(element);
		element = findElementByXpath(Constant.xpathClickItemForQuickViewByIndex);
		performMouseHover(element);
		element =findElementByXpath(Constant.xpathQuickViewItemPriceByIndexMouseHover);
		String price=element.getText();
		element=findElementByXpath(Constant.xpathQuickViewITemNameByIndexMouseHover); 
		String itemName=element.getText();
		element = findElementByXpath(Constant.xpathClickItemForQuickViewByIndex);
		performMouseHoverWithClick(element);
		List<String> itemList=new ArrayList<String>();
		itemList.add(price);
		itemList.add(itemName);
		return itemList;
	}
		
	public void clickAddToCartButtonByMouseHoverByIndex(int index,String xpathString)
	{
		scrollDown();
		element=driver.findElement(By.xpath(Constant.xpathOfItemByIndex));
		performMouseHover(element);
		element = driver.findElement(By.xpath(xpathString));
		element.click();
	}
	
	public int countNoOfItems(WebDriver driver)
	{
		WebElement itemsUlist = driver.findElement(By.xpath(Constant.xpathIndexPageHomeFeaturedProductUnorderedList));
		List<WebElement> noOfItems=itemsUlist.findElements(By.tagName("li"));
		return noOfItems.size();
	}
	
	public boolean checkMouseHoveronAllItems(WebDriver driver)
	{
		String xpathOfItems =Constant.xpathOfItemByIndex;
		String xpathOfAddToCartButtons=Constant.xpathAddToCartButtonByMouseHoverOnItemInIndexPage;
		boolean res=false;
		for(int index=Constant.addToCartButton1;index<=countNoOfItems(driver);index++)
		{
			element=driver.findElement(By.xpath(xpathOfItems));
			performMouseHover(element);
			element=element.findElement(By.xpath(xpathOfAddToCartButtons));
			if(element.isDisplayed())
			{
				res=true;
				Log.info("Element is available");
			}
			else 
			{
				res=false;
			}
		}
		return res;
	}
	
	public boolean validatePriceAddToCartMoreButtonDispalyedOrNot(WebDriver driver)
	{
		String xpathOfItems =Constant.xpathOfItemByIndex;
		String xpathOfAddToCartButtons=Constant.xpathAddToCartButtonByMouseHoverOnItemInIndexPage;
		boolean res=false;
		for(int index=Constant.addToCartButton1;index<=countNoOfItems(driver);index++)
		{
			element=driver.findElement(By.xpath(xpathOfItems));
			performMouseHover(element);
			element=element.findElement(By.xpath(xpathOfAddToCartButtons));
			WebElement buttonMore=driver.findElement(By.xpath(Constant.xpathQuickViewMoreButtonByIndexMouseHover));
			WebElement textPrice=driver.findElement(By.xpath(Constant.xpathQuickViewItemPriceByIndexMouseHover));
			if(element.isDisplayed() && buttonMore.isDisplayed() && textPrice.isDisplayed())
			{
				
				res=true;
				Log.info("Element is available");
			}
			else 
			{
				res=false;
			}
		}
		return res;
		
	}
	
	public boolean checkMouseHoveringItemNameTextIsDispalyed(WebDriver driver)
	{
		
		boolean res=false;
		for(int index=Constant.addToCartButton1;index<=countNoOfItems(driver);index++)
		{
			element=driver.findElement(By.xpath(Constant.xpathQuickViewITemNameByIndexMouseHover));
			if(element.isDisplayed())
			{
				
				res=true;
				Log.info("Element is available");
			}
			else 
			{
				res=false;
			}
		}
		return res;
	}
	
	public boolean checkQuickViewTextIsDiplayed()
	{
		String xpathOfItems =Constant.xpathOfItemByIndex;
		String xpathClickItemForQuickViewByIndex=Constant.xpathClickItemForQuickViewByIndex;
		boolean res=false;
		for(int index=Constant.item1;index<=countNoOfItems(driver);index++)
		{
			element=driver.findElement(By.xpath(xpathOfItems));
			performMouseHover(element);
			element=element.findElement(By.xpath(xpathClickItemForQuickViewByIndex));
			if(element.isDisplayed())
			{
				res=true;
				Log.info("Element is available");
			}
			else 
			{
				res=false;
			}
		}
		return res;
	}
	
	public boolean validateOfferPriceAndDiscount(int itemNo,WebDriver driver) throws Exception
	{
		util.setTestData(Constant.pathTestData+Constant.fileTestData,Constant.sheet2);
		
		return false;
	}

	
}
