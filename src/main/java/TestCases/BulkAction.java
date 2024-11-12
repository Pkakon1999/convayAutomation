package TestCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
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
import Page_Objects.ResendInvitation_Page;
import Page_Objects.SuspendUser_Page;
import Page_Objects.BulkAction_Page;
import Utilities.Take_Screenshot;

public class BulkAction {

	WebDriver driver;
	XSSFWorkbook ExcelWBook;
	XSSFSheet ExcelWSheet;
	XSSFSheet AddSingleUserSheet;

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

	@Test(priority = 1) // Test case to to perform user suspension as a bulk action (Cancel Suspension)
	void BulkAction1() throws InterruptedException {
		// Navigate to the Manage User page after login
		BulkAction_Page bulkActionPage = new BulkAction_Page(driver);

		// Open the Admin dropdown
		bulkActionPage.clickAdminDropdown();
		Thread.sleep(2000);

		// Click on "Manage Users"
		bulkActionPage.clickManageUsers();
		Thread.sleep(2000);

		// Hover over the row to make the checkbox visible
		bulkActionPage.hoverOverRow1();
		Thread.sleep(2000);

		// Click on the checkbox of an user
		bulkActionPage.select1stUser();
		Thread.sleep(2000);

		// Hover over the row to make the checkbox visible
		bulkActionPage.hoverOverRow2();
		Thread.sleep(2000);

		// Click on the checkbox of an user
		bulkActionPage.select2ndUser();
		Thread.sleep(2000);

		// Click on cross icon
		bulkActionPage.selectCloseIcon();
		Thread.sleep(2000);

		// Hover over the row to make the checkbox visible
		bulkActionPage.hoverOverRow1();
		Thread.sleep(2000);

		// Click on the checkbox of an user
		bulkActionPage.select1stUser();
		Thread.sleep(2000);

		// Hover over the row to make the checkbox visible
		bulkActionPage.hoverOverRow2();
		Thread.sleep(2000);

		// Click on the checkbox of an user
		bulkActionPage.select2ndUser();
		Thread.sleep(2000);

		// Click on the Suspend User
		bulkActionPage.clickBulkSuspend();
		Thread.sleep(2000);

		// Click on No
		bulkActionPage.clickSuspendNo();
		Thread.sleep(2000);
	}

	@AfterMethod
	public void captureFailureScreenshot1(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}

	@Test(priority = 2) // Test case to to perform user suspension as a bulk action (Perform Suspension)
	void BulkAction2() throws InterruptedException {
		// Navigate to the Manage User page after login
		BulkAction_Page bulkActionPage = new BulkAction_Page(driver);

		// Open the Admin dropdown
		bulkActionPage.clickAdminDropdown();
		Thread.sleep(2000);

		// Click on "Manage Users"
		bulkActionPage.clickManageUsers();
		Thread.sleep(2000);

		// Hover over the row to make the checkbox visible
		bulkActionPage.hoverOverRow1();
		Thread.sleep(2000);

		// Click on the checkbox of an user
		bulkActionPage.select1stUser();
		Thread.sleep(2000);

		// Hover over the row to make the checkbox visible
		bulkActionPage.hoverOverRow2();
		Thread.sleep(2000);

		// Click on the checkbox of an user
		bulkActionPage.select2ndUser();
		Thread.sleep(2000);

		// Click on the Suspend User
		bulkActionPage.clickBulkSuspend();
		Thread.sleep(2000);

		// Click on yes
		bulkActionPage.clickBulkSuspendYes();
		Thread.sleep(2000);

		String getToasterValue = bulkActionPage.getToasterValue();

		// Verify the toaster message text
		Assert.assertEquals(getToasterValue, "User(s) suspended");
	}

	@AfterMethod
	public void captureFailureScreenshot2(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}

	@Test(priority = 3) // Test case to to perform user reactivation as a bulk action (Perform Reactivation)
	void BulkAction3() throws InterruptedException {
		// Navigate to the Manage User page after login
		BulkAction_Page bulkActionPage = new BulkAction_Page(driver);
		SuspendUser_Page suspendUserPage = new SuspendUser_Page(driver);

		// Open the Admin dropdown
		bulkActionPage.clickAdminDropdown();
		Thread.sleep(2000);

		// Click on "Manage Users"
		bulkActionPage.clickManageUsers();
		Thread.sleep(2000);

		// Click on "Role Dropdown"
		Thread.sleep(2000);
		suspendUserPage.roleDropdown();
		Thread.sleep(2000);

		// Select Suspend from dropdown
		suspendUserPage.selectSuspended();
		Thread.sleep(2000);

		// Hover over the row to make the checkbox visible
		bulkActionPage.hoverOverRow1();
		Thread.sleep(2000);

		// Click on the checkbox of an user
		bulkActionPage.select1stUser();
		Thread.sleep(2000);

		// Hover over the row to make the checkbox visible
		bulkActionPage.hoverOverRow2();
		Thread.sleep(2000);

		// Click on the checkbox of an user
		bulkActionPage.select2ndUser();
		Thread.sleep(2000);

		// Click on the Suspend User
		bulkActionPage.clickActive();
		Thread.sleep(2000);

		// Click on No
		bulkActionPage.clickActiveYes();
		Thread.sleep(2000);

		String getToasterValue = bulkActionPage.getToasterValue();

		// Verify the toaster message text
		Assert.assertEquals(getToasterValue, "User(s) reactivated");
	}

	@AfterMethod
	public void captureFailureScreenshot3(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}

	@Test(priority = 4) // Test case to check resend invitation to the invited users
	void BulkAction4() throws InterruptedException {
		// Navigate to the Manage User page after login
		BulkAction_Page bulkActionPage = new BulkAction_Page(driver);
		ResendInvitation_Page resendInvitationPage = new ResendInvitation_Page(driver);

		// Open the Admin dropdown
		bulkActionPage.clickAdminDropdown();
		Thread.sleep(2000);

		// Click on "Manage Users"
		bulkActionPage.clickManageUsers();
		Thread.sleep(2000);

		// Click on "Role Dropdown"
		Thread.sleep(2000);
		resendInvitationPage.roleDropdown();
		Thread.sleep(2000);

		// Select Suspend from dropdown
		resendInvitationPage.selectInvited();
		Thread.sleep(2000);

		// Hover over the row to make the checkbox visible
		bulkActionPage.hoverOverInvitedRow1();
		Thread.sleep(2000);

		// Click on the checkbox of an user
		bulkActionPage.select1stUserResend();
		Thread.sleep(2000);

		// Hover over the row to make the checkbox visible
		bulkActionPage.hoverOverInvitedRow2();
		Thread.sleep(2000);

		// Click on the checkbox of an user
		bulkActionPage.select2ndUserResend();
		Thread.sleep(2000);

		// Click on the Suspend User
		bulkActionPage.clickResend();
		Thread.sleep(2000);

		// Click on No
		bulkActionPage.clickBulkResendYes();
		Thread.sleep(2000);

		String getToasterValue = bulkActionPage.getToasterValue();

		// Verify the toaster message text
		Assert.assertEquals(getToasterValue, "Invitation resent");
	}

	@AfterMethod
	public void captureFailureScreenshot4(ITestResult result) throws IOException {
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