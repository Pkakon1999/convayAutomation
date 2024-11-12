package TestCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Page_Objects.LogOut_Page;
import Page_Objects.Login_Page;
import Utilities.Take_Screenshot;

public class LogOutTest {

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
		driver.get("https://convay.com/sign-in");

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

	@Test(priority = 1) // Click on Name to open dropdown
	public void LogOut_Page1() throws InterruptedException {
		// Log out test
		LogOut_Page logoutPage = new LogOut_Page(driver);
		logoutPage.clickdropDown1();
		Thread.sleep(2000); // Wait for the logout to complete

		System.out.println("Button Text: " + logoutPage.getButtonText());

		logoutPage.clickLogout();
		// Wait for the redirection to the login page
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlToBe("https://convay.com/sign-in"));

		// Get and print the current URL
		String currentUrl = driver.getCurrentUrl();
		System.out.println("Current URL: " + currentUrl);

		// User redirection validation after successful sign in
		String expectedUrl = "https://convay.com/sign-in";

		Assert.assertEquals(currentUrl, expectedUrl, "URL after logout did not match the expected URL.");
	}

	@AfterMethod
	public void captureScreenshotOnFailure(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}

	@Test(priority = 2) // Click on Arrow to open dropdown
	public void LogOut_Page2() throws InterruptedException {
		// Log out test
		LogOut_Page logoutPage = new LogOut_Page(driver);
		logoutPage.clickdropDown2();
		Thread.sleep(2000); // Wait for the logout to complete

		System.out.println("Button Text: " + logoutPage.getButtonText());

		logoutPage.clickLogout();
		// Wait for the redirection to the login page b
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlToBe("https://convay.com/sign-in"));

		// Get and print the current URL
		String currentUrl = driver.getCurrentUrl();
		System.out.println("Current URL: " + currentUrl);

		// User redirection validation after successful sign in
		String expectedUrl = "https://convay.com/sign-in";

		Assert.assertEquals(currentUrl, expectedUrl, "URL after logout did not match the expected URL.");
	}

	@AfterMethod
	public void captureScreenshotOnFailure11(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}
	

	/*@Test(priority = 3) // Click on Logo to open dropdown
	public void LogOut_Page3() throws InterruptedException {
		// Log out test
		LogOut_Page logoutPage = new LogOut_Page(driver);
		logoutPage.clickdropDown3();
		Thread.sleep(2000); // Wait for the logout to complete

		System.out.println("Button Text: " + logoutPage.getButtonText());

		logoutPage.clickLogout(); // Wait for the redirection to the login page
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlToBe("https://convay.com/sign-in"));

		// Get and print the current URL
		String currentUrl = driver.getCurrentUrl();
		System.out.println("Current URL: " + currentUrl);

		// User redirection validation after successful sign in
		String expectedUrl = "https://convay.com/sign-in";

		Assert.assertEquals(currentUrl, expectedUrl, "URL after logout did not match the expected URL.");
	}

	@AfterMethod
	public void captureScreenshotOnFailure1(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}*/

	@AfterClass
	void teardown() throws IOException {
		// Close the workbook and the browser after the tests
		ExcelWBook.close();
		driver.close();
	}
}