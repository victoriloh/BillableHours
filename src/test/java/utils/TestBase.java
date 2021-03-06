package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestBase {

	public static ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();
	public static ExtentReports reports;
	public static ExtentHtmlReporter htmlReporter;
	private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> testInfo = new ThreadLocal<ExtentTest>();
	public static String toAddress;

	public static String server;
	public String local = "local";
	public static String time = null;
	public static String couple = null;
    public String remoteBrowserStack = "remote-browserStack";
    public static String userName = "USERNAME";
    public static String accessKey = "ACCESS_KEY";
    public String remoteJenkins = "remote-jenkins";

	public static AndroidDriver getDriver() {
		return driver.get();
	}

	String devices;
	static String[] udid;

	@Parameters("groupReport")
	@BeforeSuite
	public void setUp(String groupReport) {

		{
			try {
				devices = TestUtils.executeAdbCommand("adb devices");
				devices = devices.replaceAll("List of devices attached", " ");
				devices = devices.replaceAll("device", " ").trim();
				udid = devices.split(" ");
			} catch (IOException e) {
				System.out.println("No devices found: " + e.toString());

			}
		}

		htmlReporter = new ExtentHtmlReporter(new File(System.getProperty("user.dir") + groupReport));
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);

	}

	@Parameters("deviceName")
	@BeforeMethod(description = "fetch test cases name")
	public void register(Method method, String deviceName) {

		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		ExtentTest child = parentTest.get().createNode(method.getName());
		testInfo.set(child);
		String deviceVersion = getDriver().getCapabilities().getCapability("platformVersion").toString();
		String os = "Device name: " + deviceName + " Android: " + deviceVersion;
		testInfo.get().getModel().setDescription(os);
	}

	@AfterMethod(description = "to display the result after each test method")
	public void captureStatus(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshotPath = TestUtils.addScreenshot();
			testInfo.get().addScreenCaptureFromBase64String(screenshotPath);
			testInfo.get().fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP)
			testInfo.get().skip(result.getThrowable());
		else
			testInfo.get().pass(result.getName() + " Test passed");

		reports.flush();
	}

	
	@AfterSuite
	@Parameters({ "toMails", "groupReport" })
	public void cleanup(String toMails, String groupReport) {
		getDriver().quit();
	}

	@AfterClass
	public void closeApp() {
		getDriver().quit();
	}

    @BeforeClass
    @Parameters({ "systemPort", "deviceNo", "server" })
    public void startApp(String systemPort, int deviceNo, String server) throws IOException {
        if (server.equals(remoteBrowserStack)) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("device", "Samsung Galaxy S9 Plus");
            caps.setCapability("app", "bs://<hashed app-id>");
            driver.set(new AndroidDriver<AndroidElement>(
                    new URL("https://" + userName + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub"), caps));

        } else if (server.equals(remoteJenkins)) {

        } else if (server.equals(local)) {

            deviceNo = deviceNo - 1;
            while (deviceNo >= udid.length) {
                deviceNo = deviceNo - 1;
            }
			try {
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("autoGrantPermissions", true);
				capabilities.setCapability("unicodeKeyboard", true);
				capabilities.setCapability("resetKeyboard", true);
				capabilities.setCapability("noReset", true);
				capabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, systemPort);
				capabilities.setCapability(MobileCapabilityType.UDID, udid[deviceNo].trim());
				capabilities.setCapability("deviceName", "Samsung S9+");
				capabilities.setCapability("platformName", "Android");
				capabilities.setCapability("appPackage", "com.google.andriod.packageinstaller");
				capabilities.setCapability("clearSystemFiles", true);
				capabilities.setCapability("appActivity", "billable_hours_app");
				capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);

				driver.set(new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities));
				getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				System.out.println("++++++++++UIAUTOMATOR 2 DRIVER INSTANCE RUNNING++++++++++");

			} catch (WebDriverException e) {
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability(MobileCapabilityType.UDID, udid[deviceNo].trim());
				capabilities.setCapability("autoGrantPermissions", true);
				capabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, systemPort);
				capabilities.setCapability("unicodeKeyboard", true);
				capabilities.setCapability("resetKeyboard", true);
				capabilities.setCapability("noReset", false);
				capabilities.setCapability("clearSystemFiles", true);
				capabilities.setCapability("deviceName", "Samsung S9+");
				capabilities.setCapability("platformName", "Android");
				capabilities.setCapability("appPackage", "com.bundle.billable_hours_app");
				capabilities.setCapability("appActivity", "com.bundle.billable_hours_app.MainActivity");


				driver.set(new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities));
				System.out.println("++++++++++UIAUTOMATOR DRIVER INSTANCE RUNNING++++++++++++");
			}
		}
		ExtentTest parent = reports.createTest(getClass().getName());
		parentTest.set(parent);
	}
}
