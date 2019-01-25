package com.automationpractice.test;

import org.junit.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.automationpractice.pages.APIndexPage;

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
		Log.info("Creation on Index Page class");
		boolean res=indexPage.selectItem(Constant.item1,driver);	
		Assert.assertTrue(res);
		
	}
	
}
