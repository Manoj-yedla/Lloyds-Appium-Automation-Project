package infra;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import logger.Log;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Init {

	private AppiumDriverLocalService service;
	private AppiumServiceBuilder builder;

	private static ThreadLocal<AppiumDriver<MobileElement>> localDriver = new ThreadLocal<AppiumDriver<MobileElement>>();

	DesiredCapabilities capabilities = new DesiredCapabilities();
	public AppiumDriver<MobileElement> driver=null;


	/**
	 * The function is used to set Capabilities and invoke driver Server.
	 */

	public AppiumDriver<MobileElement> invokeDriver(String deviceName, String PlatformVersion, String udid, String os,
			int systemPort)
	/*
	 * public AppiumDriver<MobileElement> invokeDriver(String deviceName, String
	 * PlatformVersion, String udid, String os, String port)
	 */
	{
		try {
			
			capabilities.setCapability("platformVersion", PlatformVersion);
			capabilities.setCapability("udid", udid);
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			Log.INFO(capabilities.toString());
			
			String Url = "http://127.0.0.1:4723/wd/hub";
		
			Log.INFO(Url);
			if (os.equals("android")) {
				
				capabilities.setCapability("platformName", os);
				capabilities.setCapability(MobileCapabilityType.APP, "C:/Users/mannu/Downloads/GeneralStore.apk");//need to add apk file path
				capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.androidsample.generalstore");//com.eros.now
				capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
						"com.androidsample.generalstore.MainActivity");//com.eros.now.launchscreen.SplashActivity
				capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
				capabilities.setCapability("autoGrantPermissions", "true");
				capabilities.setCapability("autoDismissAlerts", "true");
				capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60");
				capabilities.setCapability("systemPort", systemPort);
				capabilities.setCapability(MobileCapabilityType.NO_RESET, "false");
				capabilities.setCapability(MobileCapabilityType.FULL_RESET, "false");
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				driver = new AndroidDriver<MobileElement>(new URL(Url), capabilities);

				driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

			} else if (os.equals("ios")) {
				capabilities.setCapability("platformName", os);
				capabilities.setCapability(MobileCapabilityType.APP, "");//need to add ipa file path
				capabilities.setCapability("wdaLocalPort",systemPort);
				capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
				capabilities.setCapability(MobileCapabilityType.NO_RESET, true); // noReset capability will clear the
																					// app cache and data
				driver = new IOSDriver<MobileElement>(new URL(Url), capabilities);
			}

		} catch (Exception ex) {
			Log.INFO(ex.getMessage());
		}

		setDriver(driver);

		Log.INFO("Message" + getDriver().getSessionId());
		return driver;
	}


	public AppiumDriver<MobileElement> getDriver() {
		return localDriver.get();
	}

	public void setDriver(AppiumDriver<MobileElement> driver) {
		localDriver.set(driver);

	}

	public String getDeviceProperty(String property) {

		property = getDriver().getCapabilities().getCapability(property).toString();
		return property;
	}
	
}
