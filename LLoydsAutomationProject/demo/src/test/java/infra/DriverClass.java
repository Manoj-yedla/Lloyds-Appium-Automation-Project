package infra;

import java.io.IOException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import logger.Log;

public class DriverClass extends Init {
	public AppiumDriver<MobileElement> driver;
	/**
	 * Call this method to start a new session if driver is null.
	 * 
	 * @param deviceName      Ex: iPhone 6
	 * @param PlatformVersion Ex :8.3.1
	 * @param simulator       Ex: false
	 * @param port            Ex:8012
	 * @param udid            Ex: 4df1d28f33c75f9d
	 * @param os              Ex: ios
	 * @return It will return driver server object.
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public AppiumDriver<MobileElement> driver(String deviceName, String PlatformVersion, String udid, String os,
			int systemPort) {
		Log.INFO("Inside Driver Class");
		if (getDriver() == null) {
			Log.INFO("driver is null");
			driver = invokeDriver(deviceName, PlatformVersion, udid, os, systemPort);

		} else {
			getDriver().launchApp();
		}

		Log.INFO("Session Returned by Driver: " + getDriver().getSessionId().toString());
		return getDriver();

	}

}