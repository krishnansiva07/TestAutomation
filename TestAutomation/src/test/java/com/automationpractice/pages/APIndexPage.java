package com.automationpractice.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Constant;
import utilities.Log;
import utilities.TestUtil;
import base.TestBase;

public class APIndexPage extends TestBase{
	
	
	//Initializing the page objects
	public APIndexPage(WebDriver driver) throws Exception
	{
		PageFactory.initElements(driver, this);
		util=new TestUtil();
	}
	
	//Method to click add to cart button
	public void selectItem(int index,WebDriver driver) throws InterruptedException
	{
		
	util.clickAddToCartByImageIndex(1, driver);
	}
	
	
}
