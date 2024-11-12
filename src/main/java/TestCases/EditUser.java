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
import Page_Objects.EditUser_Page;
import Utilities.Take_Screenshot;

public class EditUser {

	WebDriver driver;
	XSSFWorkbook ExcelWBook;
	XSSFSheet ExcelWSheet;
	XSSFSheet AddSingleUserSheet;
	XSSFSheet EditUserSheet;

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

	@Test(priority = 1) // Test case to edit an user
	void EditUser1() throws InterruptedException {
		// Navigate to the Manage User page after login
		EditUser_Page editUserPage = new EditUser_Page(driver);

		// Open the Admin dropdown
		editUserPage.clickAdminDropdown();
		Thread.sleep(2000);

		// Click on "Manage Users"
		editUserPage.clickManageUsers();
		Thread.sleep(2000);

		// Click on 3 dot for which user you want to edit
		editUserPage.select3dot();
		Thread.sleep(2000);

		// Click on Edit
		editUserPage.selectEditUser();
		Thread.sleep(2000);

		// Load the new sheet for user data
		EditUserSheet = ExcelWBook.getSheetAt(16);

		// Reading user data from the Excel sheet
		String firstName = EditUserSheet.getRow(0).getCell(0).toString();
		String lastName = EditUserSheet.getRow(0).getCell(1).toString();
		String jobTitle = EditUserSheet.getRow(0).getCell(2).toString();

		// Fill out the user information
		editUserPage.clearFirstName();
		editUserPage.enterFirstName(firstName);
		Thread.sleep(1000);
		
		editUserPage.clearLastName();
		editUserPage.enterLastName(lastName);
		Thread.sleep(1000);
		
		editUserPage.clearJobTitle();
		editUserPage.enterJobTitle(jobTitle);
		Thread.sleep(1000);

		// Select role
		editUserPage.selectUserRole();
		Thread.sleep(2000);

		// Click on Confirm Update
		editUserPage.clickUpdate();
		Thread.sleep(2000);

		String getToasterValue = editUserPage.getToasterValue();

		// Verify the toaster message text
		Assert.assertEquals(getToasterValue, "User edited successfully");
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