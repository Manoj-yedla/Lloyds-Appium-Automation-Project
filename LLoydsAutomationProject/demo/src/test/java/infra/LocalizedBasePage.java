package infra;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import logger.Log;
import utilities.ExtentManager;
import utilities.ExtentTestManager;
/**
 * As the class name suggest ,It is a Base page contains generic methods for
 * extent reports and logging utility. It is also used to append screen capture
 * and visual comparison logs in extent report.
 * 
 * 
 * 
 *
 */
public class LocalizedBasePage extends Init {

	public static ExtentReports extent;
	public ExtentTest test;
	private static ThreadLocal<ExtentTest> localtest = new ThreadLocal<ExtentTest>();

	/**
	 * TreadLocal getTest and setTest method are used to maintain thread safety .
	 * 
	 */
	public ExtentTest getTest() {
		return localtest.get();
	}

	/**
	 * TreadLocal getTest and setTest method are used to maintain thread safety .
	 * 
	 */

	public void setTest(ExtentTest test) {
		localtest.set(test);

	}

	public void testStart(String testName, String description) {
		test = (ExtentTestManager.startTest(testName, description).assignCategory(getPlatformName() + "_" + getDeviceName())
				.assignAuthor("Manoj_Yedla"));
		setTest(test);
	}

	/**
	 * Extent logger
	 */

	public void logTestMessage(String message) {
		try {
			getTest().log(Status.INFO, message + " : " + getDeviceName());
		} catch (Exception ex) {
			Log.INFO(ex.getMessage());
		}
	}

	/**
	 * Tear down the test logging for particular driver instance.
	 */
	public void endTest() {
Log.INFO("Ending the test");
	}

	/**
	 * Generic method to return device name only.
	 * 
	 */
	public String getDeviceName() {
		String deviceName;
		deviceName = getDriver().getCapabilities().getCapability("deviceName").toString();
		return deviceName;
	}
	/**
	 * Generic method to return platform name only.
	 * 
	 */
	public String getPlatformName()
	{
		String platformName=getDriver().getCapabilities().getCapability("platformName").toString();
		return platformName;
	}

	/**
	 * Append the captured screenshot in extent report.
	 * 
	 */
	public void logScreenCapture(String screenName) {
		
		File file=getDriver().getScreenshotAs(OutputType.FILE);
		
		byte[] encoded=null;
		try {
			encoded=Base64.encodeBase64(FileUtils.readFileToByteArray(file));
		}catch(IOException e1)
		{
			e1.printStackTrace();
		}

        getTest().log(Status.INFO, "Screenshot:",
                MediaEntityBuilder.createScreenCaptureFromBase64String(new String(encoded, StandardCharsets.US_ASCII)).build());

    }


	@BeforeMethod
	public void beforeMethod(Method m) {
		Log.INFO("/n" + "*******Starting " + m.getName() + " test******" + "/n");
	}


	/**
	 * Flush reporter logs into the report.Also append the exception logs throw by
	 * ITestResult.
	 */
	@AfterMethod
	public void afterTest(ITestResult result) {
		try {

			if (!result.isSuccess()) {
				getTest().log(Status.FAIL, result.getThrowable());
				UUID uuid = UUID.randomUUID();
				logScreenCapture(uuid.toString());
			} else {
				getTest().log(Status.PASS, "Test Passed");
			}
			
			ExtentManager.getReporter().flush();

		}

		catch (Exception ex) {
			Log.INFO("Exception at Home Page " + ex.getMessage());
		}
	}

	}