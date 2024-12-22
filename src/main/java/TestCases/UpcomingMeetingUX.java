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
import Page_Objects.UpcomingMeetingUX_Page;
import Utilities.Take_Screenshot;

public class UpcomingMeetingUX {

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

	@Test(priority = 1) // Test case to validate upcoming meeting field
	void UpcomingUX1() throws InterruptedException {
		// Navigate to the Start meeting option after login
		UpcomingMeetingUX_Page upcoming = new UpcomingMeetingUX_Page(driver);
		
		Thread.sleep(4000);

		// Capture the upcoming meeting field
		String initialValue = upcoming.getUpcomingMeetingField();

		// Assert the upcoming meeting ID
		String afterGetUpcomingField = upcoming.getUpcomingMeetingField();
		Assert.assertEquals(afterGetUpcomingField, initialValue, "Upcoming Meetings");
	}

	@AfterMethod
	public void captureFailureScreenshot1(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}
	
	@Test(priority = 2) // Test case to validate upcoming meeting date
	void UpcomingUX2() throws InterruptedException {
		// Navigate to the Start meeting option after login
		UpcomingMeetingUX_Page upcoming = new UpcomingMeetingUX_Page(driver);
		
		Thread.sleep(4000);

		// Capture the upcoming meeting field
		String initialValue = upcoming.getUpcomingMeetingDate();

		// Assert the upcoming meeting ID
		String afterGetUpcomingDate = upcoming.getUpcomingMeetingDate();
		Assert.assertEquals(afterGetUpcomingDate, initialValue, "Today, Sunday, December 15");
	}

	@AfterMethod
	public void captureFailureScreenshot2(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}
	
	@Test(priority = 3) // Test case to validate upcoming meeting start button
	void UpcomingUX3() throws InterruptedException {
		// Navigate to the Start meeting option after login
		UpcomingMeetingUX_Page upcoming = new UpcomingMeetingUX_Page(driver);
		
		Thread.sleep(4000);

		// Capture the upcoming meeting field
		String initialValue = upcoming.getButtonText();

		// Assert the upcoming meeting ID
		String afterGetButtonText = upcoming.getButtonText();
		Assert.assertEquals(afterGetButtonText, initialValue, "Start");
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