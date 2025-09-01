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
import Page_Objects.DeleteUser_Page;
import Utilities.Take_Screenshot;

public class DeleteUser {

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

	@Test(priority = 1) // Test case to delete a user
	void deleteUser() throws InterruptedException {
		// Navigate to the Manage User page after login
		DeleteUser_Page deleteUserPage = new DeleteUser_Page(driver);

		// Open the Admin dropdown
		deleteUserPage.clickAdminDropdown();
		Thread.sleep(2000);

		// Click on "Manage Users"
		deleteUserPage.clickManageUsers();
		Thread.sleep(2000);

		// Click on 3 dot for which user you want to delete
		deleteUserPage.select3dot();
		Thread.sleep(2000);

		// Click on delete
		deleteUserPage.selectDelete();
		Thread.sleep(2000);

		// Click on Confirm Suspension
		deleteUserPage.selectConfirmDeletion();
		Thread.sleep(2000);

		String getToasterValue = deleteUserPage.getToasterValue();

		// Verify the toaster message text
		Assert.assertEquals(getToasterValue, "User Deleted Successfully");
	}

	@AfterMethod
	public void captureFailureScreenshot1(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}

	@Test(priority = 2) // Test case to check deleted user is existing or not
	void checkUser() throws InterruptedException {
		// Navigate to the Manage User page after login
		DeleteUser_Page deleteUserPage = new DeleteUser_Page(driver);

		// Open the Admin dropdown
		deleteUserPage.clickAdminDropdown();
		Thread.sleep(2000);

		// Click on "Manage Users"
		deleteUserPage.clickManageUsers();
		Thread.sleep(6000);
	}

	@AfterMethod
	public void captureFailureScreenshot2(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}

	@Test(priority = 3) // Test case to check the deleted user can login or not
	void deletededUserLogin() throws InterruptedException {
		DeleteUser_Page deleteUserPage = new DeleteUser_Page(driver);

		// Login before add users
		driver.get("https://meet2.synesisit.info/sign-in");

		AddSingleUserSheet = ExcelWBook.getSheetAt(14);

		// Reading the first row's first and second cells for username and password
		String username = AddSingleUserSheet.getRow(0).getCell(0).toString();
		String password = AddSingleUserSheet.getRow(0).getCell(1).toString();

		// Perform Login (using your Login_Page)
		Login_Page lp = new Login_Page(driver);
		lp.setUserName(username);
		lp.setPassword(password);
		Thread.sleep(2000);
		lp.clickLogin();
		Thread.sleep(4000); // Wait for login to complete

		// Get and print the current URL
		String currentUrl = driver.getCurrentUrl();
		System.out.println("Current URL: " + currentUrl);

		// To check page validation
		String expectedUrl = "https://meet2.synesisit.info/sign-in";

		// To validate error message text
		String expectedText = "Account suspended. Contact your admin.";
		deleteUserPage.getMessageText(expectedText);
		Thread.sleep(2000);

		Assert.assertEquals(currentUrl, expectedUrl, "Deleted user is still able to login.");
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