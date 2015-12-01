package org.collegeboard.qotdplus;

import org.collegeboard.qotdplus.SamplePage;
import org.collegeboard.qotdplus.TestProperties;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseTest {

	StringBuffer logExceptionBuffer;
	private WebDriver driver;
	private String platform;
	private String app;
	private String env;
	private String appName;
	private Properties props = null;

    private SamplePage samplePage;


	Logger CollegeLogger = Logger.getLogger("MyLogger");

	@BeforeTest(groups = { "college-ios", "college-android", "dev"})
	public void setup() {
		platform = System.getProperty("platform");
		app = System.getProperty("app");
		//env = System.getProperty("env");
		appName = System.getProperty("appname");

		boolean runOnCloud = false;
		runOnCloud = new Boolean(System.getProperty("runoncloud"));

		CollegeLogger.log(Level.INFO, "Environment: " + platform + " App : " + app
				+ " run on cloud" + runOnCloud + "env  " + env);

		loadProperties();

		try {
			if (platform.equals(TestProperties.ANDROID)) {
				CollegeLogger.log(Level.INFO,
						"Loading Android  Desired Capabilities");
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("platformName", "Android");
				capabilities.setCapability("deviceName", "Android Emulator");
				capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
				capabilities.setCapability("platformVersion", "4.4");
				capabilities.setCapability("app", app);
				capabilities.setCapability("appPackage", props.getProperty("APP_PACKAGE"));
				capabilities.setCapability("appWaitActivity",props.getProperty("APP_ACTIVITY"));
				capabilities.setCapability("appActivity",props.getProperty("APP_ACTIVITY"));
				if (runOnCloud) {
					driver = new RemoteWebDriver(new URL(
							"http://ondemand.saucelabs.com:80/wd/hub"),
							capabilities);
				} else
					driver = new RemoteWebDriver(new URL(
							"http://127.0.0.1:4723/wd/hub"), capabilities);

			} else {
				CollegeLogger.log(Level.INFO, "Loading IOS  Desired Capabilities");
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("platformName", "iOS");
				capabilities.setCapability("platformVersion", "8.3");
				capabilities.setCapability(CapabilityType.PLATFORM, "mac");
				capabilities.setCapability("deviceName", "iPhone 6");
				capabilities.setCapability("app", app);
				capabilities.setCapability("autoAcceptAlerts", true);
				if (runOnCloud)
					driver = new RemoteWebDriver(new URL(
							"http://ondemand.saucelabs.com:80/wd/hub"),
							capabilities);
				else
					driver = new RemoteWebDriver(new URL(
							"http://127.0.0.1:4723/wd/hub"), capabilities);

			}
			Thread.sleep(5000);
				//updateEnvironment();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		CollegeLogger.log(Level.INFO, "End of Desired Capabilities");

	}

	@AfterTest
	public void tearDown() {
		try {
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logFailure(String sLineMsg) {
		logExceptionBuffer.append(sLineMsg);
		Assert.fail(logExceptionBuffer.toString() + "\n");
	}

	public String getUniqueName() {
		String uniquename = getTimeStamp();
		return uniquename;
	}

	public String getTimeStamp() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MMddyyhmmss");
		String formattedDate = sdf.format(date);
		return (formattedDate);
	}

	private void loadProperties() {
		props = new Properties();

		try {
			CollegeLogger.log(Level.INFO, "Loading Properties for " + platform + ", " + appName);
			String current = new java.io.File(".").getCanonicalPath();
			InputStream input = new FileInputStream(
					platform.equals("android") ? current
							+ "//src/main/java/resources/"+appName+".Android.properties"
							: current
									+ "//src/main/java/resources/" + appName +".Ios.properties");
			props.load(input);
			input.close();

		} catch (IOException e) {

			CollegeLogger.log(Level.INFO, "Exception loading " + platform
					+ " properties file");
			e.printStackTrace();
		}

	}

	public void updateEnvironment() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		if (platform.equals(TestProperties.ANDROID)) {
			CollegeLogger.log(Level.INFO, "Switching envionrment for " + platform
					+ " to " + env);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(props.getProperty("APP_CONF"))));
			if(!env.equals("prod"))
				{
					driver.findElement(By.name(props.getProperty("APP_CONF"))).click();
					FindElementByTagNameAndClick(driver,props.getProperty("ENV_SET"));
				}
		} else {
			CollegeLogger.log(Level.INFO, "Switching envionrment for " + platform
					+ " to " + env);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By
					.name(props.getProperty("APP_CONF"))));
			driver.findElement(By.name(props.getProperty("APP_CONF"))).click();
			if (!env.equals("prod")) {
				FindElementByTagNameAndClick(driver, props.getProperty("AWS_STAGE_MINI"));
				driver.findElement(By.name("E2E")).click();
			}
			//scroll();
			driver.findElement(By.name("Disabled")).click();
			driver.findElement(By.name("Never")).click();
			FindElementByTagNameAndClick(driver, props.getProperty("KIIP_REWARDS"));
			FindElementByTagNameAndClick(driver, props.getProperty("KIIP_REWARDS_SWITCH"));
			driver.findElement(By.name("Done")).click();
		}

	}
	
	public void findElelemntByIndexAndClick(String classType, int index) {
		/*
		Looping thru elements is not working because there are more than1 image and
		 images dont have names to them.So forced to use index
		*/
		CollegeLogger.log(Level.INFO,"findElelemntByIndexAndSendKeys to click floating icon");
		List<WebElement> listOfInputs = driver.findElements(By
				.className(classType));
				listOfInputs.get(index).click();
	}
	
	
	public void FindElementByTagNameAndClick(WebDriver pageDriver,String val) {
		StringTokenizer st = new StringTokenizer(val,",");
		   String[] splitArray = val.split(",");
		List<WebElement> listOfInputs = pageDriver.findElements(By
				.className(splitArray[0]));
		for (int i = 0; i < listOfInputs.size(); i++) {
			if (listOfInputs.get(i).getAttribute(splitArray[1]).equals(splitArray[2])){
				listOfInputs.get(i).click();
				return;
			}
		}
		}
	
	public void findElementByNameAndClick(String classType, String searchType,
			String name) {
		List<WebElement> listOfInputs = driver.findElements(By
				.className(classType));
		for (int i = 0; i < listOfInputs.size(); i++) {
			if (listOfInputs.get(i).getAttribute(searchType).equals(name)){
				listOfInputs.get(i).click();
				return;
			}

		}

	}
	
	public void findElelemntByIndexAndSendKeys(String classType, int index,String url) {
		/*
		Looping thru elements is not working because there are more than1 image and
		 images dont have names to them.So forced to use index
		*/
		CollegeLogger.log(Level.INFO,"findElelemntByIndexAndSendKeys to click floating icon");
		List<WebElement> listOfInputs = driver.findElements(By
				.className(classType));
				listOfInputs.get(index).sendKeys(url);
	}
	
	public void findElelemntByIndexAndSendKeys(String classType, int index,Keys key) {
		/*
		Looping thru elements is not working because there are more than1 image and
		 images dont have names to them.So forced to use index
		*/
		CollegeLogger.log(Level.INFO,"findElelemntByIndexAndSendKeys to click floating icon");
		List<WebElement> listOfInputs = driver.findElements(By
				.className(classType));
				listOfInputs.get(index).sendKeys(key);
	}

	private void scroll() {
		System.out.println("Before scroll");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap flickObject = new HashMap();
		flickObject.put("endX", 0);
		flickObject.put("endY", 0);
		flickObject.put("touchCount", 1);
		js.executeScript("mobile: swipe", flickObject);
		System.out.println("After scroll");
	}
	
	public void scrolldown() throws Exception {
		System.out.println("Waiting to scroll up");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap flickObject = new HashMap();
		flickObject.put("endX", 50);
		flickObject.put("endY", 50);
		flickObject.put("touchCount", 1);
		js.executeScript("mobile: swipe", flickObject);

	}

	public Properties getProperties() {
		return props;
	}

	public WebDriver getPageDriver() {
		return driver;
	}

	public String getEnvironment() {
		return platform;
	}

	// Page library initialization
    public SamplePage samplePage() {
        if (samplePage == null)
            samplePage = new SamplePage( driver,props);
        return samplePage;
    }


}
