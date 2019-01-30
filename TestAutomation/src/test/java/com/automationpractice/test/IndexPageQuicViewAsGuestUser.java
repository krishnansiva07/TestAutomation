package com.automationpractice.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utilities.Constant;
import utilities.Log;
import utilities.TestUtil;
import base.TestBase;

import com.automationpractice.pages.APIndexPage;

public class IndexPageQuicViewAsGuestUser extends TestBase{

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
	}
	
	@Test
	public void test_10100_VerifyThatMouseHoverIsHappeningOnAllItems()
	{
		Log.info("Starting the Xtent Report for test_10100_VerifyThatMouseHoverIsHappeningOnAllItems() method");
		TestUtil.extentStartTest();
		Assert.assertTrue(indexPage.validateMouseHoverOnAllItems(driver));
		Log.info("test_10100_VerifyThatMouseHoverIsHappeningOnAllItems() Passed");
	}
	
	@Test
	public void test_10101_VerifyThatWhileMouseHoveringPriceAddToCartAndMoreButtonsAreDisplayingOrNot()
	{
		Log.info("Starting the Xtent Report for test_10101_VerifyThatWhileMouseHoveringPriceAddToCartAndMoreButtonsAreDisplayingOrNot()");
		TestUtil.extentStartTest();
		Assert.assertTrue(indexPage.validatePriceAddToCartButtonAndMoreButtonIsDisplayed(driver));
		Log.info("test_10101_VerifyThatWhileMouseHoveringPriceAddToCartAndMoreButtonsAreDisplayingOrNot() Passed");
	}
	
	@Test
	public void test_10102_VerifyThatWhileMouseHoveringItemNameTextIsDispalyedOrNot()
	{
		Log.info("Starting the Xtent Report for test_10102_VerifyThatWhileMouseHoveringItemNameTextIsDispalyedOrNot()");
		TestUtil.extentStartTest();
		Assert.assertTrue(indexPage.validateMouseHoveringItemNameTextIsDispalyed(driver));
		Log.info("test_10102_VerifyThatWhileMouseHoveringItemNameTextIsDispalyedOrNot() Passed");
	}
	
	@Test
	public void test_10103_VerifyThatSevenItemsAreDisplayedInHomeFeatured() throws Exception
	{
		Log.info("Starting the Xtent Report for test_10103_VerifyThatSevenItemsAreDisplayedInHomeFeatured()");
		TestUtil.extentStartTest();
		Assert.assertTrue(indexPage.validateNumberOfItemsInHomeFeatured(driver));
		Log.info("test_10103_VerifyThatSevenItemsAreDisplayedInHomeFeatured() Passed");	
	}
	
	@Test
	public void test_10104_verifyThatQuickViewTextIsDisplayedInAllItems()
	{
		Log.info("Starting the Xtent Report for test_10104_verifyThatQuickViewTextIsDisplayedInAllItems()");
		TestUtil.extentStartTest();
		Assert.assertTrue(indexPage.validateQuickViewTextIsDiplayed(driver));
		Log.info("test_10104_verifyThatQuickViewTextIsDisplayedInAllItems() Passed");
	}
	@Test
	public void test_10105_VerifyThatDiscountPercentageIsDisplayedAtFifthAndSeventhItemsBeforMouseHoveringOrNot() throws InterruptedException
	{
		Log.info("Starting the Xtent Report for 10105_VerifyThatDiscountPercentageIsDisplayedAtFifthAndSeventhItemsBeforMouseHoveringOrNot()");
		TestUtil.extentStartTest();
		Assert.assertTrue(indexPage.validateOffer(driver));
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

}
