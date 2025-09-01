package TestCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Page_Objects.Instant_HostSetting_Page;
import Page_Objects.Login_Page;
import Utilities.Take_Screenshot;

public class InstantHostSetting {

	WebDriver driver;
	XSSFWorkbook ExcelWBook;
	XSSFSheet ExcelWSheet;
	XSSFSheet AddSingleUserSheet;

	@BeforeClass
	void setup() throws IOException {
		// Set Chrome preferences to allow microphone access globally
	    ChromeOptions options = new ChromeOptions();
	    HashMap<String, Object> contentSettings = new HashMap<>();
	    HashMap<String, Object> profile = new HashMap<>();
	    HashMap<String, Object> prefs = new HashMap<>();
	    
	    // Set microphone permission to allow
	    contentSettings.put("media_stream_mic", 1); // 1 = allow; 2 = block
	    profile.put("managed_default_content_settings", contentSettings);
	    prefs.put("profile", profile);
	    options.setExperimentalOption("prefs", prefs);
	    
	    // Additional flag to bypass microphone permission prompt
	    options.addArguments("--use-fake-ui-for-media-stream");
	    
	    // Initialize WebDriver with ChromeOptions
	    driver = new ChromeDriver(options);
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    driver.manage().window().maximize();

	    // Setup Excel file
	    File excelFile = new File("TestData\\TestDataFile.xlsx");
	    FileInputStream inputStream = new FileInputStream(excelFile);
	    ExcelWBook = new XSSFWorkbook(inputStream);
	    AddSingleUserSheet = ExcelWBook.getSheetAt(3);
	}

	@BeforeMethod
	void navigateToHomePage2() throws InterruptedException {
		// Login before add users
		driver.get("https://meet2.synesisit.info/sign-in");

		AddSingleUserSheet = ExcelWBook.getSheetAt(0);

		// Reading the first row's first and second cells for username and password
		String username = AddSingleUserSheet.getRow(0).getCell(0).toString();
		String password = AddSingleUserSheet.getRow(0).getCell(1).toString();

		// Perform Login (using your Login_Page)
		Login_Page lp = new Login_Page(driver);
		lp.setUserName(username);
		lp.setPassword(password);
		lp.clickLogin();
		Thread.sleep(20000); // Wait for login to complete
	}

	@Test(priority = 1) // Test case to access Host Control for instant meeting
	void Instant_HostControl() throws InterruptedException {
	    // Navigate to the Manage User page after login
		Instant_HostSetting_Page setting = new Instant_HostSetting_Page(driver);

	    // Click on Start Now
		setting.clickSTartInstant();
	    Thread.sleep(3000);

	    // Click on Host Control
	    setting.clickHostControl();
	    Thread.sleep(3000);

	    // Get the scrollable card container
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    WebElement cardContainer = setting.getCardContainer();

	    // Scroll down the card container incrementally
	    int scrollAmount = 100; // Adjust this value for smaller steps
		int scrollDuration = 200; // Delay in milliseconds between scroll steps

		for (int i = 0; i < 30; i++) { // Adjust the number of iterations as needed
			js.executeScript("arguments[0].scrollBy(0, arguments[1]);", cardContainer, scrollAmount);
			Thread.sleep(scrollDuration);
		}

		// Click on "Start" button
		setting.clickStart();
		Thread.sleep(20000);

		// Store the current window handle
		String originalWindow = driver.getWindowHandle();

		// Wait for the new tab to open and switch to it
		for (String windowHandle : driver.getWindowHandles()) {
			if (!originalWindow.contentEquals(windowHandle)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}

		// Close the new tab and switch back to the original window
		driver.close();
		driver.switchTo().window(originalWindow);
	}

	@AfterMethod
	public void captureFailureScreenshot1(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}

	@AfterClass
	void teardown() throws IOException {
		ExcelWBook.close();
		driver.close();
	}
}