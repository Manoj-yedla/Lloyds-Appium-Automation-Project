package test;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import infra.DriverClass;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.login;
import pageObjects.ProductList;
import infra.LocalizedBasePage;
import utilities.ExtentManager;
import utilities.MobileActions;

public class SampleTest extends LocalizedBasePage{
    private AppiumDriver<MobileElement> driver;
	private MobileActions utils;
	login ls;
	ProductList pl;

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("*****This is Login Test BeforeSuite*****");
		extent = ExtentManager.getReporter(
				new File("").getAbsolutePath() + File.separator + "Reports" + File.separator + "ExtentReport.html");

	}

	@Parameters({ "deviceName", "os", "udid", "platform", "systemPort" })
	@BeforeTest
	public void setUp(String deviceName, String os, String udid, String platform, int systemPort)
			throws InterruptedException {
		System.out.println("*****This is Login Test BeforeTest*****");
		DriverClass in = new DriverClass();
		driver = in.driver(deviceName, platform, udid, os, systemPort);
		utils = new MobileActions(driver);
		ls = new login(driver, utils);
		pl = new ProductList(driver, utils);
		
	}

	@Test (priority = 1)
	public void LoginTestNegative() throws InterruptedException
	{
		testStart("Login Screen Negative","Verify Toast Message functionality");
		ls.clickOnLetsShopButton();
		ls.getToastMessage();
	}
	
	@Test (priority = 2)
	public void LoginTest() throws InterruptedException
	{
        testStart("Login Screen","Verify login functionality");
		ls.scrollToText("Argentina");
		ls.clickOnTheCountry();
		ls.clickOnLoginButton();
        String actualHomePageTitle = ls.getTitle();
		String expectedHomePageTitle = "Products";
        Assert.assertEquals(actualHomePageTitle, expectedHomePageTitle);
        Thread.sleep(3000);
	}

	@Test (priority = 3)
	public void AddItemToTheCartTest() throws InterruptedException
	{
		testStart("Add To Cart","Verify Add Item To Cart functionality");
		pl.scrollToProduct("Jordan 6 Rings");
		pl.clickOnTheAddToCart();
		pl.clickOnTheCartButton();
		String actualCartPageTitle = pl.getCartTitle();
		String expectedCartPageTitle = "Cart";
		Assert.assertEquals(actualCartPageTitle, expectedCartPageTitle);
		endTest();
	}

}
