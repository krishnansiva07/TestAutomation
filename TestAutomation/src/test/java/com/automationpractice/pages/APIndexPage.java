package com.automationpractice.pages;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import utilities.Constant;
import utilities.ExcelUtils;
import utilities.Log;
import utilities.TestUtil;
import base.TestBase;

public class APIndexPage extends TestBase{
	
	@FindBy(xpath="//*[@id='product']/div/div/div[2]/h1")
	WebElement itemNameQuickViewPopup;
	@FindBy(xpath="//*[@id='our_price_display']")
	WebElement itemPriceQuickViewPopUp;
	@FindBy(xpath="//*[@id='homefeatured']/li[7]/div/div[2]/div[1]/span[1]")
	WebElement discountPriceItemtSeven;
	
		
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
	
	public void closeQuickViewWindow()
	{
		driver.findElement(By.cssSelector(Constant.cssSelectorForQuickViewCloseButton)).click();
	}
	
	public void clickAddToCartButtonByMouseHoverByIndexAtIndexPage(int index,WebDriver driver)
	{
		util.clickAddToCartButtonByMouseHoverByIndex(Constant.addToCartButton1,Constant.xpathAddToCartButtonByMouseHoverOnItemInIndexPage);
	}
	
	public void validateHomeFeaturedItemPictures(int index,WebDriver driver) throws IOException
	{
		element = driver.findElement(By.xpath(Constant.xpathOfItemByIndex));
		BufferedImage expectedImage = ImageIO.read(new File(System.getProperty("user.dir") +"\\Images\\1-home_default.jpg"));
		Screenshot logoImageScreenshot = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver, element);
		BufferedImage actualImage = logoImageScreenshot.getImage();  
		ImageDiffer imgDiff = new ImageDiffer();
		ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
		Assert.assertFalse(diff.hasDiff(),"Result of Image comparsion");
		
	}
	
	public boolean validateMouseHoverOnAllItems(WebDriver driver)
	{
		boolean res=util.checkMouseHoveronAllItems(driver);
		return res;
	}
	
	public boolean validatePriceAddToCartButtonAndMoreButtonIsDisplayed(WebDriver driver)
	{
		return util.validatePriceAddToCartMoreButtonDispalyedOrNot(driver);
	}
	
	public boolean validateMouseHoveringItemNameTextIsDispalyed(WebDriver driver)
	{
		return util.checkMouseHoveringItemNameTextIsDispalyed(driver);
	}
	
	public boolean validateNumberOfItemsInHomeFeatured(WebDriver driver) throws Exception{
		Log.info("Setting up the Excel sheet for read the no of itens in home featured index page");
		util.setTestData(Constant.pathTestData+Constant.fileTestData,Constant.sheet2);
		Log.info("Reading the noOfItems by sending row and column in sheet 2");
		String noOfItems=ExcelUtils.getCellData(11, 1);
		System.out.println("No Of Items"+noOfItems);
		int noOfItemsInt=Integer.parseInt(noOfItems);
		if(util.countNoOfItems(driver)==noOfItemsInt)
		{
			return true;
		}
		return false;
	}
	
	public boolean validateQuickViewTextIsDiplayed(WebDriver driver)
	{
		Log.info("Calling check checkQuickViewTextIsDiplayed()");
		return util.checkQuickViewTextIsDiplayed();
	}
	
	public boolean validateOffer(WebDriver driver) throws InterruptedException
	{
		util.scrollDown();
		Thread.sleep(5000);
		if(discountPriceItemtSeven.isDisplayed())
		{
			System.out.println(discountPriceItemtSeven.getText());
			return true;
		}
		return false;
	}
}
