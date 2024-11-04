package TestCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

import Page_Objects.Login_Page;
import Page_Objects.OrganizationLogo_Page;
import Page_Objects.ProfilePicture_Page;
import Utilities.Take_Screenshot;

public class OrganizationLogo {

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
		File excelFile = new File("D:\\Convay_Automation\\AutomationTest\\TestData\\TestDataFile.xlsx");
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

	@Test(priority = 1) // Add a profile picture
	public void OrganizationLogo1() throws InterruptedException {
		OrganizationLogo_Page organizationLogo = new OrganizationLogo_Page(driver);

		// Open dropdown to access Profile
		organizationLogo.clickdropDown();
		Thread.sleep(2000);

		// Open Profile Page
		organizationLogo.clickManageOrganization();
		Thread.sleep(2000);

		// Click on Change button to open file input
		organizationLogo.clickChange();
		Thread.sleep(2000);

		// Correct the path to the logo
		String logo = "D:\\Convay_Automation\\AutomationTest\\TestData\\convay1.png";

		// Upload the logo by sending the file path directly to the input element
		organizationLogo.uploadProfilePicture(logo);
		Thread.sleep(2000); // Wait for the file to upload

		// Click Add button after uploading
		organizationLogo.clickAdd();
		Thread.sleep(2000);

		String getToasterValue = organizationLogo.getToasterValue();

		// Verify the toaster message text
		Assert.assertEquals(getToasterValue, "Organization logo updated successfully");
	}

	@AfterMethod
	public void captureScreenshotOnFailure(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}

	@Test(priority = 2) // Remove a logo
	public void OrganizationLogo2() throws InterruptedException {
		OrganizationLogo_Page organizationLogo = new OrganizationLogo_Page(driver);

		// Open dropdown to access Profile
		organizationLogo.clickdropDown();
		Thread.sleep(2000);

		// Open Profile Page
		organizationLogo.clickManageOrganization();
		Thread.sleep(2000);

		// Click on Change button to open file input
		organizationLogo.clickRemove();
		Thread.sleep(2000);
		
		String getToasterValue2 = organizationLogo.getToasterValue2();

		// Verify the toaster message text
		Assert.assertEquals(getToasterValue2, "Organization logo removed successfully");

	}

	@AfterMethod
	public void captureScreenshotOnFailure1(ITestResult result) throws IOException {
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
