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

import Page_Objects.EditMeetingInfo_Page;
import Page_Objects.Login_Page;
import Utilities.Take_Screenshot;

public class EditMeetingInfo {

	WebDriver driver;
	XSSFWorkbook ExcelWBook;
	XSSFSheet ExcelWSheet;

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
		ExcelWSheet = ExcelWBook.getSheetAt(0);
	}

	@BeforeMethod
	void navigateToHomePage() throws InterruptedException {
		// Login before each logout test
		driver.get("https://meet2.synesisit.info/sign-in");

		// Reading the first row's first and second cells for username and password
		String username = ExcelWSheet.getRow(0).getCell(0).toString();
		String password = ExcelWSheet.getRow(0).getCell(1).toString();

		// Perform Login (using your Login_Page)
		Login_Page lp = new Login_Page(driver);
		lp.setUserName(username);
		lp.setPassword(password);
		lp.clickLogin();
		Thread.sleep(2000); // Wait for login to complete
	}

	@Test(priority = 1)
	public void EditMeetingInfo1() throws InterruptedException {
	    EditMeetingInfo_Page editMeetingPage = new EditMeetingInfo_Page(driver);

	    // Open dropdown to access Profile
	    editMeetingPage.clickdropDown();
	    Thread.sleep(2000);

	    // Open Profile Page
	    editMeetingPage.clickProfile();
	    Thread.sleep(2000);

	    // Click on Edit button to change PMU
	    editMeetingPage.clickEditPMU();
	    Thread.sleep(2000);

	    // Capture the current state before cancelling (e.g., text or value of PMU field)
	    String initialPMUValue = editMeetingPage.getPMUFieldValue();

	    // Click Cancel button
	    editMeetingPage.clickCancel();
	    Thread.sleep(2000);

	    // Assert that the PMU field value remains the same after cancelling
	    String afterCancelPMUValue = editMeetingPage.getPMUFieldValue();
	    Assert.assertEquals(afterCancelPMUValue, initialPMUValue, "PMU value changed after cancelling the edit.");
	}

	@AfterMethod
	public void captureScreenshotOnFailure(ITestResult result) throws IOException {
	    if (ITestResult.FAILURE == result.getStatus()) {
	        Take_Screenshot.TakeScreenshot(driver, result.getName());
	    }
	}


	@Test(priority = 2) // Click on edit button and edit the PMU
	public void EditMeetingInfo2() throws InterruptedException {
		EditMeetingInfo_Page editMeetingPage = new EditMeetingInfo_Page(driver);

		// Open dropdown to access Profile
		editMeetingPage.clickdropDown();
		Thread.sleep(2000);

		// Open Profile Page
		editMeetingPage.clickProfile();
		Thread.sleep(2000);

		// Click on Edit button to change PMU
		editMeetingPage.clickEditPMU();
		Thread.sleep(2000);

		// Load the new sheet for user data
		ExcelWSheet = ExcelWBook.getSheetAt(5);

		// Reading user data from the Excel sheet
		String firstName = ExcelWSheet.getRow(0).getCell(0).toString();
		
		editMeetingPage.clearPMU();
		Thread.sleep(2000);

		// Fill out the user information
		editMeetingPage.setPMU(firstName);
		Thread.sleep(3000);

		// Click on edit button
		editMeetingPage.clickSaveChange();
		Thread.sleep(2000);
		
		String initialPMUValue = editMeetingPage.getPMUFieldValue();
		
		// Assert that the PMU field value remains the same after cancelling
	    String afterEditPMUValue = editMeetingPage.getPMUFieldValue();
	    Assert.assertEquals(afterEditPMUValue, initialPMUValue, "PMU value did not changed after edit.");
	}

	@AfterMethod
	public void captureScreenshotOnFailure2(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}

	@AfterClass
	void teardown() throws IOException {
		// Close the workbook and the browser after the tests
		ExcelWBook.close();
		driver.close();
	}

}
