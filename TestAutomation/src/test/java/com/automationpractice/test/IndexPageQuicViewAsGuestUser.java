package com.automationpractice.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utilities.Constant;
import utilities.ExcelUtils;
import utilities.Log;
import utilities.TestUtil;
import base.TestBase;

import com.automationpractice.pages.APIndexPage;

public class IndexPageQuicViewAsGuestUser extends TestBase{

	public ExcelUtils excelUtil;
	public IndexPageQuicViewAsGuestUser() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@BeforeTest
	public void setup() throws Exception
	{
		TestUtil.startReport();
		Log.info("Extent Repeort Started");
		driver=super.initialization();
		indexPage=new APIndexPage(driver);
		excelUtil=new ExcelUtils();
	}
	
	@Test(enabled=false)
	public void test_10100_VerifyThatMouseHoverIsHappeningOnAllItems()
	{
		Log.info("Starting the Xtent Report for test_10100_VerifyThatMouseHoverIsHappeningOnAllItems() method");
		TestUtil.extentStartTest();
		Assert.assertTrue(indexPage.validateMouseHoverOnAllItems(driver));
		Log.info("test_10100_VerifyThatMouseHoverIsHappeningOnAllItems() Passed");
	}
	
	@Test(enabled=false)
	public void test_10101_VerifyThatWhileMouseHoveringPriceAddToCartAndMoreButtonsAreDisplayingOrNot()
	{
		Log.info("Starting the Xtent Report for test_10101_VerifyThatWhileMouseHoveringPriceAddToCartAndMoreButtonsAreDisplayingOrNot()");
		TestUtil.extentStartTest();
		Assert.assertTrue(indexPage.validateMouseHoverOnAllItems(driver));
		Log.info("test_10101_VerifyThatWhileMouseHoveringPriceAddToCartAndMoreButtonsAreDisplayingOrNot() Passed");
	}
	
	@Test(enabled=false)
	public void test_10102_VerifyThatWhileMouseHoveringItemNameTextIsDispalyedOrNot()
	{
		Log.info("Starting the Xtent Report for test_10102_VerifyThatWhileMouseHoveringItemNameTextIsDispalyedOrNot()");
		TestUtil.extentStartTest();
		Assert.assertTrue(indexPage.validateMouseHoveringItemNameTextIsDispalyed(driver));
		Log.info("test_10102_VerifyThatWhileMouseHoveringItemNameTextIsDispalyedOrNot() Passed");
	}
	
	@Test(enabled=false)
	public void test_10103_VerifyThatSevenItemsAreDisplayedInHomeFeatured() throws Exception
	{
		Log.info("Starting the Xtent Report for test_10103_VerifyThatSevenItemsAreDisplayedInHomeFeatured()");
		TestUtil.extentStartTest();
		Assert.assertTrue(indexPage.validateMouseHoveringItemNameTextIsDispalyed(driver));
		Log.info("test_10103_VerifyThatSevenItemsAreDisplayedInHomeFeatured() Passed");	
	}
	
	@Test(enabled=true)
	public void test_10104_verifyThatQuickViewTextIsDisplayedInAllItems() throws InterruptedException
	{
		Log.info("Starting the Xtent Report for test_10104_verifyThatQuickViewTextIsDisplayedInAllItems()");
		TestUtil.extentStartTest();
		Assert.assertTrue(indexPage.validateQuickViewTextIsDiplayed(driver));
		Log.info("test_10104_verifyThatQuickViewTextIsDisplayedInAllItems() Passed");
	}
	@Test(dataProvider="itemDiscountDetails",enabled=false)
	public void test_10105_VerifyThatDiscountPercentageIsDisplayedAtFifthAndSeventhItemsBeforMouseHoveringOrNot(String itemNo,String afterDiscount,String beforeDiscount,String discountPercentage) throws InterruptedException
	{
		Log.info("Starting the Xtent Report for 10105_VerifyThatDiscountPercentageIsDisplayedAtFifthAndSeventhItemsBeforMouseHoveringOrNot()");
		TestUtil.extentStartTest();
		int itemNoInteger=Integer.parseInt(itemNo);
		Assert.assertTrue(indexPage.validateOfferBeforeMouseHover(driver,itemNoInteger,afterDiscount,beforeDiscount,discountPercentage));
		Log.info("test_10105_VerifyThatDiscountPercentageIsDisplayedAtFifthAndSeventhItemsBeforMouseHoveringOrNot() Passed");
	}
	@Test(dataProvider="itemDiscountDetails",enabled=false)
	public void test_10106_VerifyThatDiscountPercentageIsDisplayedAtFifthAndSeventhItemsAfterMouseHoveringOrNot(String itemNo,String afterDiscount,String beforeDiscount,String discountPercentage)
	{
		Log.info("Starting the Xtent Report for 10106_VerifyThatDiscountPercentageIsDisplayedAtFifthAndSeventhItemsAfterMouseHoveringOrNot()");
		TestUtil.extentStartTest();
		int itemNoInteger=Integer.parseInt(itemNo);
		Assert.assertTrue(indexPage.validateOfferAfterMouseHover(driver, itemNoInteger, afterDiscount, beforeDiscount, discountPercentage));
		Log.info("test_10106_VerifyThatDiscountPercentageIsDisplayedAtFifthAndSeventhItemsAfterMouseHoveringOrNot() Passed");
	}
	@AfterMethod
	public void checkStatus(ITestResult result) throws IOException
	{
		Log.info("Validating the test result after test Method");
		TestUtil.checkStatus(result);
		
	}
		 
	@AfterTest
	public void tearDown() {
		extent.flush();
		extent.close();
		driver.close();
	}
	
	@DataProvider(name="itemDiscountDetails")
	public Object[][] getDiscountDetails()
	{
		Object[][] data=excelUtil.getExcelTableArray(Constant.pathTestData+Constant.fileTestData,Constant.sheet2,17, 0, 2, 4);
		return data;
	}
}
