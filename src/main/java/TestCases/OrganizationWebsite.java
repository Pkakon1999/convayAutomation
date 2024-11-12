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

import Page_Objects.Login_Page;
import Page_Objects.OrganizationWebsite_Page;
import Utilities.Take_Screenshot;

public class OrganizationWebsite {

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

	@Test(priority = 1) // To check information is edited or not after clicking on cancel button
	public void OrganizationWebsite1() throws InterruptedException {
		OrganizationWebsite_Page organizationWebsite = new OrganizationWebsite_Page(driver);

		// Open dropdown to access Profile
		organizationWebsite.clickdropDown();
		Thread.sleep(2000);

		// Open Profile Page
		organizationWebsite.clickManageOrganization();
		Thread.sleep(2000);

		// Use JavaScriptExecutor to scroll down by 300 pixels
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300);");
		Thread.sleep(1000);

		// Click on Edit button to edit address
		organizationWebsite.clickEdit();
		Thread.sleep(2000);

		// Use JavaScriptExecutor to scroll down by 300 pixels
		js.executeScript("window.scrollBy(0,300);");
		Thread.sleep(1000);

		// Click Cancel button
		organizationWebsite.clickCancel();
		Thread.sleep(2000);
	}

	@AfterMethod
	public void captureScreenshotOnFailure(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}

	@Test(priority = 2)
	public void OrganizationWebsite2() throws InterruptedException {
		OrganizationWebsite_Page organizationWebsite = new OrganizationWebsite_Page(driver);

		// Open dropdown to access Profile
		organizationWebsite.clickdropDown();
		Thread.sleep(2000);

		// Open Profile Page
		organizationWebsite.clickManageOrganization();
		Thread.sleep(2000);
		
		// Use JavaScriptExecutor to scroll down by 300 pixels
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300);");
		Thread.sleep(1000);

		// Click on Edit button to change profile info
		organizationWebsite.clickEdit();
		Thread.sleep(2000);

		// Use JavaScriptExecutor to scroll down by 300 pixels
		js.executeScript("window.scrollBy(0,300);");
		Thread.sleep(1000);

		// Load the new sheet for user data
		ExcelWSheet = ExcelWBook.getSheetAt(11);

		// Reading new user data from the Excel sheet
		String website = ExcelWSheet.getRow(0).getCell(0).toString();
		String vanityUrl = ExcelWSheet.getRow(0).getCell(1).toString();

		// Clear and set new Website and Vanity URL
		organizationWebsite.clearWebsite();
		organizationWebsite.setWebsite(website);
		Thread.sleep(1000);

		organizationWebsite.clearVanityUrl();
		organizationWebsite.setVanityUrl(vanityUrl);
		Thread.sleep(1000);

		// Click Save Changes button
		organizationWebsite.clickSave();
		Thread.sleep(2000);

		String getToasterValue = organizationWebsite.getToasterValue();

		// Verify the toaster message text
		Assert.assertEquals(getToasterValue, "Organization info updated successfully");
	}

	@AfterMethod
	public void captureScreenshotOnFailure2(ITestResult result) throws IOException {
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
