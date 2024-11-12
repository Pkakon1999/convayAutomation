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
import Page_Objects.OrganizationAddress_Page;
import Utilities.Take_Screenshot;

public class OrganizationAddress {

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
	public void OrganizationAddress1() throws InterruptedException {
		OrganizationAddress_Page organizationAddress = new OrganizationAddress_Page(driver);

		// Open dropdown to access Profile
		organizationAddress.clickdropDown();
		Thread.sleep(2000);

		// Open Profile Page
		organizationAddress.clickManageOrganization();
		Thread.sleep(2000);

		// Click on Edit button to edit address
		organizationAddress.clickEdit();
		Thread.sleep(2000);

		// Use JavaScriptExecutor to scroll down by 300 pixels
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300);");
		Thread.sleep(1000);

		// Click Cancel button
		organizationAddress.clickCancel();
		Thread.sleep(2000);
	}

	@AfterMethod
	public void captureScreenshotOnFailure(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}

	@Test(priority = 2)
	public void OrganizationAddress2() throws InterruptedException {
	    OrganizationAddress_Page organizationAddress = new OrganizationAddress_Page(driver);

	    // Open dropdown to access Profile
	    organizationAddress.clickdropDown();
	    Thread.sleep(2000);

	    // Open Profile Page
	    organizationAddress.clickManageOrganization();
	    Thread.sleep(2000);

	    // Click on Edit button to change profile info
	    organizationAddress.clickEdit();
	    Thread.sleep(2000);
	    
	    // Use JavaScriptExecutor to scroll down by 300 pixels
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(0,300);");
	    Thread.sleep(1000);

	    // Load the new sheet for user data
	    ExcelWSheet = ExcelWBook.getSheetAt(10);

	    // Reading new user data from the Excel sheet
	    String location = ExcelWSheet.getRow(0).getCell(0).toString();
	    String city = ExcelWSheet.getRow(0).getCell(1).toString();
	    String state = ExcelWSheet.getRow(0).getCell(2).toString();

	    // Clear and set new Location, City, State, and Country
	    organizationAddress.clearLocation();
	    organizationAddress.setLocation(location);
	    Thread.sleep(1000);

	    organizationAddress.clearCity();
	    organizationAddress.setCity(city);
	    Thread.sleep(1000);

	    organizationAddress.clearState();
	    organizationAddress.setState(state);
	    Thread.sleep(1000);
	    
	    organizationAddress.clickDropdown();
	    Thread.sleep(2000);
	    
	    organizationAddress.clickCountry();
	    Thread.sleep(2000);

	    // Click Save Changes button
	    organizationAddress.clickSave();
	    Thread.sleep(2000);

	    String getToasterValue = organizationAddress.getToasterValue();

	    // Verify the toaster message text
	    Assert.assertEquals(getToasterValue, "Organization location updated successfully");
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
