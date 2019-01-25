package com.automationpractice.pages;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Constant;
import utilities.Log;
import utilities.TestUtil;
import base.TestBase;

public class APIndexPage extends TestBase{
	
	@FindBy(xpath="//*[@id='product']/div/div/div[2]/h1")
	WebElement itemNameQuickViewPopup;
	@FindBy(xpath="//*[@id='our_price_display']")
	WebElement itemPriceQuickViewPopUp;
	
	//Initializing the page objects
	public APIndexPage(WebDriver driver) throws Exception
	{
		PageFactory.initElements(driver, this);
		util=new TestUtil();
	}
	
	//Method to click add to cart button
	public boolean selectItem(int index,WebDriver driver) throws InterruptedException
	{
		
	List<String> itemDetails=util.clickAddToCartByImageIndexQuickView(index, driver);
	if(itemDetails.get(0).equals(Constant.nameOfItem1) && itemDetails.get(1).equals(Constant.itemPrice1));
	{
		return true;
	}
	}
	
	
}
