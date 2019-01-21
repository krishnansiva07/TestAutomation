package com.automationpractice.pages;

import org.openqa.selenium.support.PageFactory;

import base.TestBase;

public class APIndexPage extends TestBase{

	//Initializing the page objects
	public APIndexPage() throws Exception
	{
		PageFactory.initElements(driver, this);
	}
}
