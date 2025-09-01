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
import Page_Objects.SuspendUser_Page;
import Utilities.Take_Screenshot;

public class SuspendUser {

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

	@Test(priority = 1) // Test case to suspend a user
	void FilterByStatus_Active() throws InterruptedException {
		// Navigate to the Add User page after login
		SuspendUser_Page suspendUserPage = new SuspendUser_Page(driver);

		// Open the Admin dropdown
		suspendUserPage.clickAdminDropdown();
		Thread.sleep(2000);

		// Click on "Manage Users"
		suspendUserPage.clickManageUsers();
		Thread.sleep(4000);

		// Click on "Role Dropdown"
		suspendUserPage.roleDropdown();
		Thread.sleep(4000);

		// Select Active from dropdown
		suspendUserPage.selectActive();
		Thread.sleep(4000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800);");
		Thread.sleep(4000);

		// Click on 3 dot for which user you want to suspend
		suspendUserPage.select3dot();
		Thread.sleep(2000);

		// Click on Suspend
		suspendUserPage.selectSuspend();
		Thread.sleep(2000);

		ExcelWSheet = ExcelWBook.getSheet("Suspension_Reason");

		// Reading new user data from the Excel sheet to set the reason
		String reason = ExcelWSheet.getRow(0).getCell(0).toString();

		suspendUserPage.setReason(reason);
		Thread.sleep(2000);

		// Click on Confirm Suspension
		suspendUserPage.selectConfirmSuspension();
		Thread.sleep(2000);

		String getToasterValue = suspendUserPage.getToasterValue();

		// Verify the toaster message text
		Assert.assertEquals(getToasterValue, "User(s) suspended");
	}

	@AfterMethod
	public void captureFailureScreenshot1(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}

	@Test(priority = 2) // Test case to filter Suspended user from status dropdown
	void FilterByStatus_Suspended() throws InterruptedException {
		// Navigate to the Add User page after login
		SuspendUser_Page suspendUserPage = new SuspendUser_Page(driver);

		// Open the Admin dropdown
		suspendUserPage.clickAdminDropdown();
		Thread.sleep(2000);

		// Click on "Manage Users"
		suspendUserPage.clickManageUsers();
		Thread.sleep(2000);

		// Click on role dropdown
		Thread.sleep(2000);
		suspendUserPage.roleDropdown();
		Thread.sleep(2000);

		// Select suspended user from dropdown
		suspendUserPage.selectSuspended();
		Thread.sleep(2000);

	}

	@AfterMethod
	public void captureFailureScreenshot2(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}

	@Test(priority = 3) // Test case to check the suspended user can login or not
	void SuspendedUserLogin() throws InterruptedException {

		// Login before add users
		driver.get("https://meet2.synesisit.info/sign-in");

		ExcelWSheet = ExcelWBook.getSheet("Suspension_Reason");;

		// Reading the first row's first and second cells for username and password
		String username = ExcelWSheet.getRow(1).getCell(0).toString();
		String password = ExcelWSheet.getRow(1).getCell(1).toString();

		// Perform Login (using your Login_Page)
		Login_Page sp = new Login_Page(driver);
		sp.setUserName(username);
		sp.setPassword(password);
		Thread.sleep(2000);
		sp.clickLogin();
		Thread.sleep(4000); // Wait for login to complete

		// Get and print the current URL
		String currentUrl = driver.getCurrentUrl();
		System.out.println("Current URL: " + currentUrl);

		// To check page validation
		String expectedUrl = "https://meet2.synesisit.info/sign-in";

		Assert.assertEquals(currentUrl, expectedUrl, "Suspended user is still able to login.");
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