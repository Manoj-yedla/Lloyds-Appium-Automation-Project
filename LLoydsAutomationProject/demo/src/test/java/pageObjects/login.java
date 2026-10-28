package pageObjects;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.testng.Assert;
import utilities.MobileActions;

public class login {

    @AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
    @iOSXCUITFindBy(xpath = "")
    public MobileElement userName;
	
	@AndroidFindBy(id="com.czprime:id/btn_login_with_same")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"SIGN IN\"]")
    public MobileElement password;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	@iOSXCUITFindBy(xpath = "")
    public MobileElement loginButton;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
	public MobileElement getHomePageTitle;

	@AndroidFindBy(id="android:id/text1")
	public MobileElement chooseCountryName;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Argentina\"]")
	public MobileElement countryName;

	@AndroidFindBy(xpath = "(//android.widget.Toast)[1]")
	public MobileElement toastMessage;


	public AppiumDriver<MobileElement> driver;
	public MobileActions utility;


    public login(AppiumDriver<MobileElement> driver,MobileActions utility)
	{
		this.driver=driver;
		this.utility=utility;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public void clickOnLetsShopButton()
	{
		loginButton.click();
		utility.logINFO("test");

	}
    
    public void clickOnLoginButton()
	{
		//Log.INFO("Clicking on Login with same user button");
		userName.sendKeys("testuser");
        //password.sendKeys("testpassword");
		driver.hideKeyboard();
        loginButton.click();
		utility.logINFO("test");
		
	}

    public String getTitle()
    {
        String title = this.getHomePageTitle.getText();
        return title;
    }

	public void scrollToText(String text)
	{
		chooseCountryName.click();
		utility.scrollToText(text);
	}

	public void clickOnTheCountry()
	{
		countryName.click();
	}

	public void getToastMessage()
	{
		String ts = toastMessage.getAttribute("name");
		Assert.assertEquals(ts,"Please enter your name");
	}
}
