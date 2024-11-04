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
import Page_Objects.ResetPasswordByAdmin_Page;
import Utilities.Take_Screenshot;

public class ResetPasswordByAdmin {

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
		File excelFile = new File("D:\\Convay_Automation\\AutomationTest\\TestData\\TestDataFile.xlsx");
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

	@Test(priority = 1) // Test case to reset PASSWORD
	void ResetPassword1() throws InterruptedException {
		// Navigate to the Manage User page after login
		ResetPasswordByAdmin_Page resetPasswordPage = new ResetPasswordByAdmin_Page(driver);

		// Open the Admin dropdown
		resetPasswordPage.clickAdminDropdown();
		Thread.sleep(2000);

		// Click on "Manage Users"
		resetPasswordPage.clickManageUsers();
		Thread.sleep(2000);

		// Use JavaScriptExecutor to scroll down by 500 pixels
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800);");
		Thread.sleep(1000);

		// Click on 3 dot for which user you want to delete
		resetPasswordPage.select3dot();
		Thread.sleep(2000);

		// Click on delete
		resetPasswordPage.selectResetPassword();
		Thread.sleep(2000);

		// Load the new sheet for user data
		ExcelWSheet = ExcelWBook.getSheetAt(15);

		// Reading new user data from the Excel sheet
		String newPass = ExcelWSheet.getRow(0).getCell(0).toString();
		String confirmPass = ExcelWSheet.getRow(0).getCell(1).toString();

		resetPasswordPage.setNewPassword(newPass);
		Thread.sleep(1000);

		resetPasswordPage.setConfirmPassword(confirmPass);
		Thread.sleep(1000);

		// Click on Confirm Update
		resetPasswordPage.clickUpdate();
		Thread.sleep(2000);

		String getToasterValue = resetPasswordPage.getToasterValue();

		// Verify the toaster message text
		Assert.assertEquals(getToasterValue, "User password updated successfully");
	}

	@AfterMethod
	public void captureFailureScreenshot1(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}

	@Test(priority = 2) // Test case to check the user can login with the old password
	void ResetPassword2() throws InterruptedException {

		// Login before add users
		driver.get("https://meet2.synesisit.info/sign-in");

		AddSingleUserSheet = ExcelWBook.getSheetAt(15);

		// Reading the first row's first and second cells for username and password
		String username = AddSingleUserSheet.getRow(1).getCell(0).toString();
		String password = AddSingleUserSheet.getRow(1).getCell(1).toString();

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

		Assert.assertEquals(currentUrl, expectedUrl, "User is still able to login with old password.");
	}

	@AfterMethod
	public void captureFailureScreenshot2(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}
	
	@Test(priority = 3) // Test case to check the user can login with the updated password
	void ResetPassword3() throws InterruptedException {

		// Login before add users
		driver.get("https://meet2.synesisit.info/sign-in");

		AddSingleUserSheet = ExcelWBook.getSheetAt(15);

		// Reading the first row's first and second cells for username and password
		String username = AddSingleUserSheet.getRow(2).getCell(0).toString();
		String password = AddSingleUserSheet.getRow(2).getCell(1).toString();

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
		String expectedUrl = "https://meet2.synesisit.info/home";

		Assert.assertEquals(currentUrl, expectedUrl, "User is not able to login with updated password.");
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