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
import Page_Objects.OrganizationInfo_Page;
import Utilities.Take_Screenshot;

public class OrganizationInfo {

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

	@Test(priority = 1)//To check information is edited or not after clicking on cancel button
	public void OrganizationInfo1() throws InterruptedException {
		OrganizationInfo_Page organizationInfo = new OrganizationInfo_Page(driver);

		// Open dropdown to access Profile
		organizationInfo.clickdropDown();
		Thread.sleep(2000);

		// Open Profile Page
		organizationInfo.clickManageOrganization();
		Thread.sleep(2000);

		// Click on Edit button to change profile info
		organizationInfo.clickEdit();
		Thread.sleep(2000);

		// Click Cancel button
		organizationInfo.clickCancel();
		Thread.sleep(2000);
	}

	@AfterMethod
	public void captureScreenshotOnFailure(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}

	@Test(priority = 2)
	public void OrganizationInfo2() throws InterruptedException {
		OrganizationInfo_Page organizationInfo = new OrganizationInfo_Page(driver);

		// Open dropdown to access Profile
		organizationInfo.clickdropDown();
		Thread.sleep(2000);

		// Open Profile Page
		organizationInfo.clickManageOrganization();
		Thread.sleep(2000);

		// Click on Edit button to change profile info
		organizationInfo.clickEdit();
		Thread.sleep(2000);

		// Load the new sheet for user data
		ExcelWSheet = ExcelWBook.getSheet("Organization_Info");

		// Reading new user data from the Excel sheet
		String firstName = ExcelWSheet.getRow(0).getCell(0).toString();

		// Clear and set new First Name, Last Name, and Phone Number
		organizationInfo.clearName();
		organizationInfo.setName(firstName);
		Thread.sleep(1000);

		// Click Save Changes button
		organizationInfo.clickSave();
		Thread.sleep(2000);
		
		String getToasterValue = organizationInfo.getToasterValue();

		// Verify the toaster message text
		Assert.assertEquals(getToasterValue, "Organization name updated successfully");

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
