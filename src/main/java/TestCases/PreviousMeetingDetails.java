package TestCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Page_Objects.Login_Page;
import Page_Objects.PreviousMeetingDetails_Page;
import Utilities.Take_Screenshot;

public class PreviousMeetingDetails {

	WebDriver driver;
	XSSFWorkbook ExcelWBook;
	XSSFSheet ExcelWSheet;
	XSSFSheet AddSingleUserSheet;

	// Inside the setup method

	@BeforeClass
	void setup() throws IOException {
		// Initialize WebDriver and Excel workbook
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		// Setup Excel file
		File excelFile = new File("TestData\\TestDataFile.xlsx");
		FileInputStream inputStream = new FileInputStream(excelFile);

		// Load the workbook and sheet
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
		Thread.sleep(4000); // Wait for login to complete
	}

	@Test(priority = 1) // Test case to validate host name of previous meeting
	void PreviousDetailsHost() throws InterruptedException {
		// Navigate to the Start meeting option after login
		PreviousMeetingDetails_Page previous = new PreviousMeetingDetails_Page(driver);

		// Click on meeting management
		previous.clickMeetingManagement();
		Thread.sleep(4000);

		// Click on Previous Meetings
		previous.clickPreviousMeetings();
		Thread.sleep(4000);

		// Capture the meeting Host name of the previous meeting
		String initialValue = previous.getHost();

		// Assert the prvious meeting ID
		String afterGetHost = previous.getHost();
		Assert.assertEquals(afterGetHost, initialValue, "Host: Kakon Paul Avi");
	}

	@AfterMethod
	public void captureFailureScreenshot1(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}

	@Test(priority = 2) // Test case to get ID of previous meeting
	void PreviousDetailsID() throws InterruptedException {
		// Navigate to the Start meeting option after login
		PreviousMeetingDetails_Page previous = new PreviousMeetingDetails_Page(driver);

		// Click on meeting management
		previous.clickMeetingManagement();
		Thread.sleep(4000);

		// Click on Previous Meetings
		previous.clickPreviousMeetings();
		Thread.sleep(4000);

		// Capture the meeting ID of the previous meeting
		String initialValue = previous.getID();

		// Assert the previous meeting ID
		String afterGetID = previous.getID();
		Assert.assertEquals(afterGetID, initialValue, "Meeting ID- 6477 1284 0966");
	}

	@AfterMethod
	public void captureFailureScreenshot2(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}

	@Test(priority = 3) // Test case to get URL of previous meeting
	void PreviousDetailsURL() throws InterruptedException {
		// Navigate to the Start meeting option after login
		PreviousMeetingDetails_Page previous = new PreviousMeetingDetails_Page(driver);

		// Click on meeting management
		previous.clickMeetingManagement();
		Thread.sleep(4000);

		// Click on Previous Meetings
		previous.clickPreviousMeetings();
		Thread.sleep(4000);

		// Capture the meeting URL of the previous meeting
		String initialValue = previous.getURL();

		// Assert the previous meeting ID
		String afterGetURL = previous.getURL();
		Assert.assertEquals(afterGetURL, initialValue,"Meeting URL- https://meet2.synesisit.info/m/j/647712840966/kakonpaulavi");
	}

	@AfterMethod
	public void captureFailureScreenshot3(ITestResult result) throws IOException {
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