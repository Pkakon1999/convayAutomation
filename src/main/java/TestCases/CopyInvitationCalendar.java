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
import Page_Objects.CopyInvitationCalendar_Page;
import Utilities.Take_Screenshot;

public class CopyInvitationCalendar {

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
		Thread.sleep(6000); // Wait for login to complete
	}
	
	@Test(priority = 1) // Test case to save a schedule meeting
	void SaveSchedule() throws InterruptedException {
		// Navigate to the Start meeting option after login
		CopyInvitationCalendar_Page calendar = new CopyInvitationCalendar_Page(driver);

		// Click on Scheduler
		calendar.clickScheduler();
		Thread.sleep(6000);

		// Click on Save button
		calendar.clickSave();
		Thread.sleep(3000);

		// Click on OK button
		calendar.clickOK();
		Thread.sleep(4000);

		String getToasterValue = calendar.getToasterValue();

		// Verify the toaster message text
		Assert.assertEquals(getToasterValue,"Successfully scheduled Kakon Paul Avi's scheduled meeting");

	}

	@AfterMethod
	public void captureFailureScreenshot1(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}

	@Test(priority = 2) // Test case to validate Copy meeting invitation from calendar
	void copyInvitation() throws InterruptedException {
		// Navigate to the Start meeting option after login
		CopyInvitationCalendar_Page calendar = new CopyInvitationCalendar_Page(driver);

		// Click on Scheduler
		calendar.clickScheduler();
		Thread.sleep(2000);

		// Click on Cancel button
		calendar.clickCancel();
		Thread.sleep(2000);

		// Click on Meeting
		calendar.clickMeeting();
		Thread.sleep(2000);

		// Click on Copy Invitation
		calendar.clickCopy();
		Thread.sleep(2000);

		String getToasterValue = calendar.getToasterValue();

		// Verify the toaster message text
		Assert.assertEquals(getToasterValue,
				"Invitation copied to clipboard\n" + "Kakon Paul Avi is inviting you to a meeting on Convay.\n"
						+ "Time: Dec 12, 2024, 12:00 PM Asia, Dhaka\n" + "Meeting ID:\n" + "6477 1284 0966\n"
						+ "Meeting Link: https://meet2.synesisit.info/m/j/647712840966/kakonpaulavi");

	}

	@AfterMethod
	public void captureFailureScreenshot2(ITestResult result) throws IOException {
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