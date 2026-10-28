package utilities;
import java.util.*;

import logger.Log;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import infra.LocalizedBasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class MobileActions extends LocalizedBasePage {
	private SoftAssert softAssertion;

	public MobileActions(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
	}

	private AppiumDriver<MobileElement> driver;
	private String cryptoNameUnderTest;

	Logger LogNew = Logger.getLogger(Log.class.getName());

	/*
	 * this method will print messages in Console logs and as well as will log to
	 * EXTENT REPORTS using logTestMessage() method
	 */
	public void logINFO(String Message) {
		DOMConfigurator.configure("log4j.xml");
		LogNew.info(Message);
		logTestMessage(Message);
	}


	/**
	 * Method to verify if the particular element is not present on the screen
	 * 
	 * @param elementIdValue
	 * @param failMessage
	 */
	public void verifyElementIsNotPresent(String elementIdValue, String failMessage) {
		int count = driver.findElements(By.id(elementIdValue)).size();
		if (count == 0) {
			Assert.assertTrue(true);
		} else {
			Assert.fail(failMessage);
		}
	}

	public void clickOnBackButtonOfDevice() {
		// ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
		((AndroidDriver) driver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
}

	public void deviceLock() {
		try {
			((AndroidDriver<MobileElement>) driver).lockDevice();
		} catch (Exception ex) {
			System.out.println("Issue while locking device");
		}
	}

	public void deviceUnLock() {
		try {
			((AndroidDriver<MobileElement>) driver).unlockDevice();
		} catch (Exception ex) {
			System.out.println("Issue while unlocking device");
		}
	}

	/*
	 * Newly added method for scroll to text
	 */
	public void scrollToText(String text) {
		Log.INFO("Scrolling to text: " + text);
		((AndroidDriver<MobileElement>) driver).findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""
						+ text + "\").instance(0))");
	}

	public boolean swipeToDirection(MobileElement el, String direction) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			HashMap<String, String> swipeObject = new HashMap<String, String>();
			if (direction.equalsIgnoreCase("down")) {
				swipeObject.put("direction", "down");
			} else if (direction.equalsIgnoreCase("up")) {
				swipeObject.put("direction", "up");
			} else if (direction.equalsIgnoreCase("left")) {
				swipeObject.put("direction", "left");
			} else if (direction.equalsIgnoreCase("right")) {
				swipeObject.put("direction", "right");
			}
			swipeObject.put("element", el.getId());
			js.executeScript("mobile:swipe", swipeObject);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void hideKeyboard() {
		try {
			driver.hideKeyboard();
		} catch (Exception ex) {
			Log.ERROR("Soft Keyboard not present..During HideKeyboard action");
		}
	}

	public void softAssert(String actual, String expected) {
		softAssert().assertEquals(actual, expected);

	}

	public SoftAssert softAssert() {
		if (softAssertion == null) {
			return softAssertion = new SoftAssert();
		}
		return softAssertion;
	}

	public void assertAll() {
		softAssert().assertAll();
	}

}
