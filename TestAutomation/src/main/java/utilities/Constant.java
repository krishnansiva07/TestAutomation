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
	public static final String pathTestData="D://Selenium_Workspace//TestAutomation//src//main//java//testdata//";
	public static final String fileTestData="TestData.xlsx";
	public static final String userName="Siva";
}
