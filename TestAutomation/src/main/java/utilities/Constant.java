package utilities;

import base.TestBase;

public class Constant extends TestBase{

	public Constant() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;
	public static final String sheet1="Sheet1";
	public static final String sheet2="Sheet2";
	public static final String browserNameChrome="chrome";
	public static final String browserNamefirefox="firefox";
	public static final String chromeExepath="D:\\Selenium_Workspace\\TestAutomation\\drivers\\chromedriver.exe";
	public static final String currentDir = System.getProperty("user.dir");
	public static final String screenshotsFolderName="/Screenshots/";
	public static final String driversFolderName="/drivers/";
	public static final String chromeDriverName="chromedriver.exe";
	public static final String firefoxDriverName="geckodriver.exe";
	public static final String pathTestData="D://TestAutomation//TeatAutomation//TestAutomation//src//main//java//testdata//";
	public static final String fileTestData="TestData.xlsx";
	public static final String userName="siva";
	
	//Index page
	public static final String xpathIndexPageHomeFeaturedProductUnorderedList="//*[@id='homefeatured']";
	public static final int addToCartButton1=1;
	public static final int addToCartButton2=2;
	public static final int addToCartButton3=3;
	public static final int addToCartButton4=4;
	public static final int addToCartButton5=5;
	public static final int addToCartButton6=6;
	public static final int addToCartButton7=7;
	public static final int addToCartButton8=8;
	
	public static final int item1=1;
	public static final int item2=2;
	public static final int item3=3;
	public static final int item4=4;
	public static final int item5=5;
	public static final int item6=6;
	public static final int item7=7;
	public static final int item8=8;
	
	
	//Constant Selectors
	public static final String xpathClickItemForQuickViewByIndex="//*[@id='homefeatured']/li['+index+']/div/div[1]/div/a[2]/span";
	public static final String xpathQuickViewItemPriceByIndexMouseHover="//*[@id='homefeatured']/li['+index+']/div/div[1]/div/div[2]/span";
	public static final String xpathQuickViewITemNameByIndexMouseHover="//*[@id='homefeatured']/li['+index+']/div/div[2]/h5/a";
	public static final String xpathQuickViewMoreButtonByIndexMouseHover="//*[@id='homefeatured']/li['+index+']/div/div[2]/div[2]/a[2]/span";
	public static final String xpathOfItemByIndex="//*[@id='homefeatured']/li['+index+']/div/div[1]/div/a[1]/img";
	public static final String cssSelectorForQuickViewCloseButton="body#index div.fancybox-overlay.fancybox-overlay-fixed > div > div > a";
	public static final String xpathAddToCartButtonByMouseHoverOnItemInIndexPage="//div[@class='button-container']/a[@title='Add to cart']";
	
	//Before Mouse hovering item discount and original price xpath
	public static final String xpathOfItemPriceAndDiscountPercentage="//*[@id='homefeatured']/li['+itemNo+']/div/div[1]/div/div[2]/span['+index+']";	
			
	public static final String nameOfItem1="Faded Short Sleeve T-shirts";
	public static final String itemPrice1="$16.51";
	
	
}
