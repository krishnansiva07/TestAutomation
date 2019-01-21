package com.automationpractice.test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.automationpractice.pages.APIndexPage;

import utilities.Log;
import utilities.TestUtil;
import base.TestBase;

public class GuestUserCheckoutTestSuite extends TestBase{
	
	APIndexPage index;
	public GuestUserCheckoutTestSuite() throws Exception
	{
		super();
	}
	
	@BeforeTest
	public void setup() throws Exception
	{
		TestUtil.startReport();
		Log.info("Extent Repeort Started");
		super.initialization();
	}
	
	@Test
	public void test_10002_VerifyThetUserCanAbleToClickAddToCartButtonAndConfirm() throws Exception
	{
		index=new APIndexPage();
		Log.info("Creation od Index Page class");
		
	}
	
}
