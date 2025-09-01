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
import Page_Objects.DeleteUpcomingMeeting_Page;
import Utilities.Take_Screenshot;

public class DeleteUpcomingMeeting {

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

	@Test(priority = 1) // Test case to validate Delete upcoming meeting
	void copyInvitation() throws InterruptedException {
		// Navigate to the upcoming meeting section after login
		DeleteUpcomingMeeting_Page delete = new DeleteUpcomingMeeting_Page(driver);

		// Click on three dot
		delete.clickThreeDot();
		Thread.sleep(2000);

		// Click on Delete Meeting
		delete.clickDelete();
		Thread.sleep(2000);

		// Click on Cancel button
		delete.clickCancel();
		Thread.sleep(2000);

		// Click on three dot
		delete.clickThreeDot();
		Thread.sleep(2000);

		// Click on Delete Meeting
		delete.clickDelete();
		Thread.sleep(2000);

		// Click on Delete button
		delete.clickDeleteConfirm();
		Thread.sleep(2000);
		
		// Capture the current text after delete meeting
	    String initialValue = delete.getValue();
		
		// Assert that the upcoming meeting field value
	    String afterDeleteValue = delete.getValue();
	    Assert.assertEquals(afterDeleteValue, initialValue, "No Upcoming meeting!");
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