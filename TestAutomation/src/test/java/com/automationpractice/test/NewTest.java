package com.automationpractice.test;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import utilities.Constant;
import utilities.ExcelUtils;
import utilities.Log;
import utilities.TestUtil;
import base.TestBase;

public class NewTest extends TestBase {

	Log log;

	public NewTest() throws Exception {
		super();
		Log.endLog("Sample");
		Log.startLog("Sample");
		
	}

	
	@BeforeTest
	public void setUpReport() throws Exception {
		Log.info("Before Starting the Extent Report");
		TestUtil.startReport();
		super.initialization();
		
	}

	@Test
	public void f() throws Exception {
		logger = extent.startTest("Test Case Name","Description Of the Test");
		
		WebElement element = driver.findElement(By.xpath("//*[@id='header']/div[2]/div/div/nav/div[1]/a"));
		element.click();
		
		WebElement userId=driver.findElement(By.xpath("//*[@id='email']"));
		WebElement passwd=driver.findElement(By.xpath("//*[@id='passwd']"));
		WebElement submit=driver.findElement(By.xpath("//*[@id='SubmitLogin']"));
		
		ExcelUtils.setExcelFile(Constant.pathTestData+Constant.fileTestData,"Sheet1");
		
		String userName=ExcelUtils.getCellData(1, 2);
		String passwrd=ExcelUtils.getCellData(1, 3);
		
		userId.sendKeys(userName);
		passwd.sendKeys(passwrd);
		submit.click();
		Assert.assertEquals(driver.getTitle(),"My account - My Store");
		logger.log(LogStatus.PASS, "Test Case Passed");
		ExcelUtils.setCellData("Pass", 1, 4);
	}
	
	@Test
	public void logOff1()
	{
		Assert.assertTrue(true);
	}
	
	@Test
	public void logOff2()
	{
		Assert.assertTrue(true);
	}
	@Test
	public void idDispalyed()
	{
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='homeslider']/li[2]/div/p[2]/button")).isDisplayed());
	}

	@AfterMethod
	public void checkStatus(ITestResult result) throws IOException {

		TestUtil.checkStatus(result);
	}

	@AfterTest
	public void tearDown() {
		extent.flush();
		extent.close();
		driver.close();
	}
}
