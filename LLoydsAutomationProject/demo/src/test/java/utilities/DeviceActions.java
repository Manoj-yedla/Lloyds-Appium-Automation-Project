package utilities;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import logger.Log;
import infra.Init;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

/**
 * A Generic utility to control device actions. Such as Handling of
 * gestures,keyboard actions ,Wait conditions,snapshot.
 * 
 * @author Harshit Jhureley
 *
 */
public class DeviceActions extends Init {

	AppiumDriver<MobileElement> driver;
	public String deviceSerial = null;
	String filePath = null;

	public String snapShot(String Screen) {

		deviceSerial = getDeviceProperty("udid");
		Log.INFO("Taking Spanshot of " + deviceSerial);
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		try {

			filePath = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + deviceSerial
					+ File.separator + Screen + ".png";
			Log.INFO(filePath);
			FileUtils.copyFile(srcFile, new File(filePath));
			sleep(1000);
		} catch (Exception ex) {
			Log.INFO("InException");
			Log.INFO(ex.getMessage());
		}

		return filePath;
	}

	public String snapShot(String Screen, String ScreenType, Boolean uiTest) {

		deviceSerial = getDeviceProperty("udid");
		Log.INFO("Taking Spanshot of " + deviceSerial);
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			if (uiTest == false) {

				filePath = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator
						+ deviceSerial + File.separator + Screen + ".png";
			} else {

				filePath = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator
						+ deviceSerial + File.separator + Screen + File.separator + ScreenType + ".png";

			}
			Log.INFO(filePath);
			FileUtils.copyFile(srcFile, new File(filePath));
			sleep(4000);
		} catch (Exception ex) {
			Log.INFO("InException");
			Log.INFO(ex.getMessage());
		}

		return filePath;
	}

	public void sleep(int timeout) {
		try {
			Thread.sleep(timeout);
		} catch (Exception ex) {
			Log.INFO(ex.getMessage());
		}
	}

}
