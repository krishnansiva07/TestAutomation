package com.automationpractice.test;

import java.io.IOException;

import org.junit.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.automationpractice.pages.APIndexPage;
import com.relevantcodes.extentreports.LogStatus;

import utilities.Constant;
import utilities.Log;
import utilities.TestUtil;
import base.TestBase;

public class GuestUserCheckoutTestSuite extends TestBase{
	
	APIndexPage indexPage;
	public GuestUserCheckoutTestSuite() throws Exception
	{
		super();
		
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
	public void test_10002_VerifyThetUserCanAbleToClickQuickViewItemAndVerifyTheDetails() throws Exception
	{
		TestUtil.extentStartTest();
		boolean res=indexPage.selectItem(Constant.item1,driver);	
		Assert.assertTrue(res);
		Thread.sleep(5000);
		indexPage.closeQuickViewWindow();
	}
	
	@Test
	public void  test_10022_VerifyThatUserCanAbleToClickAddToCarTButtonFromIndexPageByMouseHoveringTheImageAndVerifyTheDetails()
	{
		TestUtil.extentStartTest();
		indexPage.clickAddToCartButtonByMouseHoverByIndexAtIndexPage(Constant.addToCartButton1, driver);
	}
	
	@Test(enabled=false)
	public void test_10023_Verify_ThatHomeFeaturedImagesAreDisplayedProperly() throws IOException
	{
		TestUtil.extentStartTest();
		indexPage.validateHomeFeaturedItemPictures(Constant.addToCartButton1,driver);
		
	}
	
	
	@AfterMethod
	public void checkStatus(ITestResult result) throws IOException
	{
		TestUtil.checkStatus(result);
	}
		
	@AfterTest
	public void tearDown() {
		extent.flush();
		extent.close();
		driver.close();
	}
}
