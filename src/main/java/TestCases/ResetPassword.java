package TestCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Page_Objects.ResetPassword_Page;
import Page_Objects.Login_Page;
import Utilities.Take_Screenshot;

public class ResetPassword {

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
		ExcelWSheet = ExcelWBook.getSheetAt(0); // Assume the user data is in the first sheet
	}

	@BeforeMethod
	void navigateToHomePage() throws InterruptedException {
		// Login before each test
		driver.get("https://meet2.synesisit.info/sign-in");

		// Reading the first row's first and second cells for username and password
		String username = ExcelWSheet.getRow(0).getCell(0).toString();
		String password = ExcelWSheet.getRow(0).getCell(1).toString();

		// Perform Login
		Login_Page lp = new Login_Page(driver);
		lp.setUserName(username);
		lp.setPassword(password);
		lp.clickLogin();
		Thread.sleep(2000); // Wait for login to complete
	}

	@Test(priority = 1) //To check password is reset or not after clicking on cancel button
	public void ResetPassword() throws InterruptedException {
		ResetPassword_Page resetPasswordPage = new ResetPassword_Page(driver);

		// Open dropdown to access Profile
		resetPasswordPage.clickdropDown();
		Thread.sleep(2000);

		// Open Profile Page
		resetPasswordPage.clickProfile();
		Thread.sleep(2000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300);");
		Thread.sleep(2000);

		// Click on Edit button to Reset Password
		resetPasswordPage.clickEditPassword();
		Thread.sleep(2000);

		// Click Cancel button
		resetPasswordPage.clickCancel();
		Thread.sleep(2000);

	}

	@AfterMethod
	public void captureScreenshotOnFailure(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}

	@Test(priority = 2) //To check password is reset or not without old password
	public void ResetPassword2() throws InterruptedException {
		ResetPassword_Page resetPasswordPage = new ResetPassword_Page(driver);

		// Open dropdown to access Profile
		resetPasswordPage.clickdropDown();
		Thread.sleep(2000);

		// Open Profile Page
		resetPasswordPage.clickProfile();
		Thread.sleep(2000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300);");
		Thread.sleep(2000);

		// Click on Edit button to Reset Password
		resetPasswordPage.clickEditPassword();
		Thread.sleep(2000);

		// Load the new sheet for user data
		ExcelWSheet = ExcelWBook.getSheetAt(7);

		// Reading new user data from the Excel sheet
		String oldPass = ExcelWSheet.getRow(0).getCell(0).toString();
		String newPass = ExcelWSheet.getRow(0).getCell(1).toString();
		String confirmPass = ExcelWSheet.getRow(0).getCell(2).toString();

		
		resetPasswordPage.setOldPassword(oldPass);
		Thread.sleep(1000);

		resetPasswordPage.setNewPassword(newPass);
		Thread.sleep(1000);

		resetPasswordPage.setConfirmPassword(confirmPass);
		Thread.sleep(1000);

		// Click Save Changes button
		resetPasswordPage.clickSaveChange();
		Thread.sleep(2000);
		
		String getToasterValue = resetPasswordPage.getToasterValue();
		
		// Verify the toaster message text
	    Assert.assertEquals(getToasterValue, "Old password did not match");

	}

	@AfterMethod
	public void captureScreenshotOnFailure2(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}
	
	@Test(priority = 3) //To check password is reset or not with different new and confirm password
	public void ResetPassword3() throws InterruptedException {
		ResetPassword_Page resetPasswordPage = new ResetPassword_Page(driver);

		// Open dropdown to access Profile
		resetPasswordPage.clickdropDown();
		Thread.sleep(2000);

		// Open Profile Page
		resetPasswordPage.clickProfile();
		Thread.sleep(2000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300);");
		Thread.sleep(2000);

		// Click on Edit button to Reset Password
		resetPasswordPage.clickEditPassword();
		Thread.sleep(2000);

		// Load the new sheet for user data
		ExcelWSheet = ExcelWBook.getSheetAt(7);

		// Reading new user data from the Excel sheet
		String oldPass = ExcelWSheet.getRow(1).getCell(0).toString();
		String newPass = ExcelWSheet.getRow(1).getCell(1).toString();
		String confirmPass = ExcelWSheet.getRow(1).getCell(2).toString();

		
		resetPasswordPage.setOldPassword(oldPass);
		Thread.sleep(1000);

		resetPasswordPage.setNewPassword(newPass);
		Thread.sleep(1000);

		resetPasswordPage.setConfirmPassword(confirmPass);
		Thread.sleep(1000);

		// Click Save Changes button
		resetPasswordPage.clickSaveChange();
		Thread.sleep(2000);
	}

	@AfterMethod
	public void captureScreenshotOnFailure3(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}
	
	@Test(priority = 4) //To check password is reset or not with old password
	public void ResetPassword4() throws InterruptedException {
		ResetPassword_Page resetPasswordPage = new ResetPassword_Page(driver);

		// Open dropdown to access Profile
		resetPasswordPage.clickdropDown();
		Thread.sleep(2000);

		// Open Profile Page
		resetPasswordPage.clickProfile();
		Thread.sleep(2000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300);");
		Thread.sleep(2000);

		// Click on Edit button to Reset Password
		resetPasswordPage.clickEditPassword();
		Thread.sleep(2000);

		// Load the new sheet for user data
		ExcelWSheet = ExcelWBook.getSheetAt(7);

		// Reading new user data from the Excel sheet
		String oldPass = ExcelWSheet.getRow(2).getCell(0).toString();
		String newPass = ExcelWSheet.getRow(2).getCell(1).toString();
		String confirmPass = ExcelWSheet.getRow(2).getCell(2).toString();

		
		resetPasswordPage.setOldPassword(oldPass);
		Thread.sleep(1000);

		resetPasswordPage.setNewPassword(newPass);
		Thread.sleep(1000);

		resetPasswordPage.setConfirmPassword(confirmPass);
		Thread.sleep(1000);

		// Click Save Changes button
		resetPasswordPage.clickSaveChange();
		Thread.sleep(2000);
		
		String getToasterValue = resetPasswordPage.getToasterValue();
		
		// Verify the toaster message text
	    Assert.assertEquals(getToasterValue, "Password updated successfully");

	}

	@AfterMethod
	public void captureScreenshotOnFailure4(ITestResult result) throws IOException {
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
