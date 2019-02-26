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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.*;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import com.thoughtworks.selenium.webdriven.commands.KeyEvent;

import base.TestBase;

public class TestUtil extends TestBase {

	public TestUtil() throws Exception {
		super();
		action = new Actions(driver);
		// TODO Auto-generated constructor stub
	}

	public void setTestData(String Path, String fileNamw) throws Exception {
		ExcelUtils.setExcelFile(Constant.pathTestData + Constant.fileTestData,
				Constant.sheet2);
	}

	public static String getBrowserName() throws Exception {
		ExcelUtils.setExcelFile(Constant.pathTestData + Constant.fileTestData,
				Constant.sheet2);
		String browserName = ExcelUtils.getCellData(2, 1);
		Log.info("After Reading Browser Name from TestData Excel");
		return browserName;
	}

	public static String getURL() throws Exception {
		ExcelUtils.setExcelFile(Constant.pathTestData + Constant.fileTestData,
				Constant.sheet2);
		String homePageURL = ExcelUtils.getCellData(1, 1);
		System.out.println("URL is" + homePageURL);
		return homePageURL;
	}

	public static void maximizeBrowser(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public static String takeScreenshotAtEndOfTest(String testMethodName)
			throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss")
				.format(new Date());
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		System.out.print(currentDir);
		String screenshotPath = Constant.currentDir
				+ Constant.screenshotsFolderName + testMethodName + dateName
				+ ".png";
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

	// This method should be called after method
	public static void checkStatus(org.testng.ITestResult result)
			throws IOException {
		if (result.getStatus() == org.testng.ITestResult.SUCCESS) {
			logger.log(LogStatus.PASS, result.getName());
		} else if (result.getStatus() == org.testng.ITestResult.FAILURE) {
			String screenshotPath = TestUtil.takeScreenshotAtEndOfTest(result
					.getName());
			logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));
			logger.log(LogStatus.FAIL,
					"Test Case Failed is " + result.getThrowable());

		} else if (result.getStatus() == org.testng.ITestResult.SKIP) {
			String screenshotPath = TestUtil.takeScreenshotAtEndOfTest(result
					.getName());
			logger.log(LogStatus.SKIP, logger.addScreenCapture(screenshotPath));
		}
		extent.endTest(logger);
	}

	public WebElement findElementByXpath(String xpath, WebDriver driver) {
		element = driver.findElement(By.xpath(xpath));
		return element;
	}

	public boolean checkElementIsPresent(WebElement element) {
		boolean res = element.isEnabled();
		return res;
	}

	public void performMouseHover(WebElement element) {
		Log.info("Mouse Hovering without click");
		action.moveToElement(element).build().perform();
	}

	public void performMouseHoverWithClick(WebElement element) {
		Log.info("Mouse Hovering with click");
		action.moveToElement(element).click().build().perform();
	}

	public void javaScriptExecutorClick(WebElement element, WebDriver driver) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	// Perform scroll down
	public void scrollDown(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");

	}

	public void scrollUntilElementIsVisible(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	public List<String> clickAddToCartByImageIndexQuickView(int index,
			WebDriver driver) {
		String xpathQuickViewITemNameByIndexMouseHover = "//*[@id='homefeatured']/li['"
				+ index + "']/div/div[2]/h5/a";
		// scrollDown();
		element = findElementByXpath(Constant.xpathOfItemByIndex, driver);
		performMouseHover(element);
		element = findElementByXpath(
				Constant.xpathClickItemForQuickViewByIndex, driver);
		performMouseHover(element);
		element = findElementByXpath(
				Constant.xpathQuickViewItemPriceByIndexMouseHover, driver);
		String price = element.getText();
		element = findElementByXpath(xpathQuickViewITemNameByIndexMouseHover,
				driver);
		String itemName = element.getText();
		element = findElementByXpath(
				Constant.xpathClickItemForQuickViewByIndex, driver);
		performMouseHoverWithClick(element);
		List<String> itemList = new ArrayList<String>();
		itemList.add(price);
		itemList.add(itemName);
		return itemList;
	}

	public void clickAddToCartButtonByMouseHoverByIndex(int index,
			String xpathString) {
		scrollDown(driver);
		element = driver.findElement(By.xpath(Constant.xpathOfItemByIndex));
		performMouseHover(element);
		element = driver.findElement(By.xpath(xpathString));
		element.click();
	}

	public int countNoOfItems(WebDriver driver) {
		WebElement itemsUlist = driver
				.findElement(By
						.xpath(Constant.xpathIndexPageHomeFeaturedProductUnorderedList));
		List<WebElement> noOfItems = itemsUlist.findElements(By.tagName("li"));
		return noOfItems.size();
	}

	public boolean checkMouseHoveronAllItems(WebDriver driver) {
		List<WebElement> elmntimg = driver.findElements(By
				.xpath("//img[@class='replace-2x img-responsive']"));
		boolean res = false;

		for (int ix = 0; ix < elmntimg.size(); ix++) {
			Actions action = new Actions(driver);
			action.moveToElement(elmntimg.get(ix)).build().perform();
			System.out.println("Item By index"
					+ elmntimg.get(ix).getAttribute("alt"));
			WebElement elecart = driver
					.findElement(By
							.xpath("//div[@class='button-container']/a[@title='Add to cart']"));
			if (elecart.isDisplayed())
				res = true;
			System.out.println("Element is available");
		}
		return res;
	}

	public boolean validatePriceAddToCartMoreButtonDispalyedOrNot(
			WebDriver driver) {
		String xpathOfItems = Constant.xpathOfItemByIndex;
		String xpathOfAddToCartButtons = Constant.xpathAddToCartButtonByMouseHoverOnItemInIndexPage;
		boolean res = false;
		for (int index = Constant.addToCartButton1; index <= countNoOfItems(driver); index++) {
			element = driver.findElement(By.xpath(xpathOfItems));
			performMouseHover(element);
			element = element.findElement(By.xpath(xpathOfAddToCartButtons));
			WebElement buttonMore = driver.findElement(By
					.xpath(Constant.xpathQuickViewMoreButtonByIndexMouseHover));
			WebElement textPrice = driver.findElement(By
					.xpath(Constant.xpathQuickViewItemPriceByIndexMouseHover));
			if (element.isDisplayed() && buttonMore.isDisplayed()
					&& textPrice.isDisplayed()) {

				res = true;
				Log.info("Element is available");
			} else {
				res = false;
			}
		}
		return res;

	}

	public boolean checkMouseHoveringItemNameTextIsDispalyed(WebDriver driver) {

		boolean res = false;
		List<WebElement> itemNamesElement = driver.findElements(By
				.xpath("//*[@class='product-name']"));
		int noOfItems = itemNamesElement.size();
		System.out.println("Number Of Items" + noOfItems);
		for (int index = 1; index <= noOfItems; index++) {
			String xpathItemText = "//*[@id='homefeatured']/li['" + index
					+ "']/div/div[2]/h5/a";
			element = util.findElementByXpath(xpathItemText, driver);
			if (element.isDisplayed()) {
				return true;
			}
		}
		return res;
	}
	
	public WebElement checkElementIsVisible(WebDriver driver,String xpath,int sec)
	{
		WebDriverWait wait = new WebDriverWait(driver,sec);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		return element;
	}

	public boolean checkQuickViewTextIsDiplayed() throws InterruptedException {

		boolean res = false;
		int index=1;
			List<WebElement> elmntimg = driver.findElements(By
					.xpath("//img[@class='replace-2x img-responsive']"));
			
			for (int ix = 0; ix < elmntimg.size(); ix++) {
				Actions action = new Actions(driver);
				action.moveToElement(elmntimg.get(ix)).build().perform();
				Thread.sleep(5);
				System.out.println("Index Value"+index);
				WebElement quickViewText =driver.findElement(By.xpath("//*[@id='homefeatured']/li['" + index + "']/div/div[1]/div/a[2]"));
				System.out.println(quickViewText.getText());
				if (quickViewText.isDisplayed())
				{
					res = true;
					index=index+1;
					System.out.println("Element is available");
				}
				else
				{
					System.out.println("Element is not available");
					return false;
				}
			}

		return res;
	}

	public boolean validateOfferPriceAndDiscount(int itemNo, WebDriver driver)
			throws Exception {
		util.setTestData(Constant.pathTestData + Constant.fileTestData,
				Constant.sheet2);

		return false;
	}

}
