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
import Page_Objects.AddSingleUser_Page;
import Page_Objects.Login_Page;
import Utilities.Take_Screenshot;

public class AddSingleUser {

	WebDriver driver;
	XSSFWorkbook ExcelWBook;
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

	@Test(priority = 1) // Test case for adding as an user
	void testAddUser1() throws InterruptedException {
		// Navigate to the Add User page after login
		AddSingleUser_Page addUserPage = new AddSingleUser_Page(driver);

		// Open the Admin dropdown
		addUserPage.clickAdminDropdown();
		Thread.sleep(2000);

		// Click on "Manage Users"
		addUserPage.clickManageUsers();
		Thread.sleep(2000);

		// Click on "Add Single User"
		addUserPage.clickAddSingleUser();
		Thread.sleep(2000);

		// Load the new sheet for user data
		AddSingleUserSheet = ExcelWBook.getSheetAt(4);

		// Reading user data from the Excel sheet
		String firstName = AddSingleUserSheet.getRow(0).getCell(0).toString();
		String lastName = AddSingleUserSheet.getRow(0).getCell(1).toString();
		String email = AddSingleUserSheet.getRow(0).getCell(2).toString();
		String jobTitle = AddSingleUserSheet.getRow(0).getCell(3).toString();

		// Fill out the user information
		addUserPage.enterFirstName(firstName);
		Thread.sleep(1000);
		addUserPage.enterLastName(lastName);
		Thread.sleep(1000);
		addUserPage.enterEmail(email);
		Thread.sleep(1000);
		addUserPage.enterJobTitle(jobTitle);
		Thread.sleep(1000);

		// Select role
		addUserPage.selectUserRole();
		Thread.sleep(2000);

		// Click on "Add User" button to submit the form
		addUserPage.clickAddUser();
		Thread.sleep(2000);

		// Verify if user is successfully added by checking redirection or a success
		// message
		String currentUrl = driver.getCurrentUrl();
		String expectedUrl = "https://meet2.synesisit.info/user-management"; // Expected URL after adding a user

		Assert.assertEquals(currentUrl, expectedUrl, "User not added successfully, URL did not match expected.");
	}

	@AfterMethod
	public void captureFailureScreenshot1(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}

	@Test(priority = 2) // Test case for adding an admin
	void testAddUser2() throws InterruptedException {

		// Navigate to the Add User page after login
		AddSingleUser_Page addUserPage = new AddSingleUser_Page(driver);

		// Open the Admin dropdown
		addUserPage.clickAdminDropdown();
		Thread.sleep(2000);

		// Click on "Manage Users"
		addUserPage.clickManageUsers();
		Thread.sleep(2000);

		// Click on "Add Single User"
		addUserPage.clickAddSingleUser();
		Thread.sleep(2000);

		// Load the new sheet for user data
		AddSingleUserSheet = ExcelWBook.getSheetAt(4);

		// Reading user data from the Excel sheet
		String firstName = AddSingleUserSheet.getRow(1).getCell(0).toString();
		String lastName = AddSingleUserSheet.getRow(1).getCell(1).toString();
		String email = AddSingleUserSheet.getRow(1).getCell(2).toString();
		String jobTitle = AddSingleUserSheet.getRow(1).getCell(3).toString();

		// Fill out the user information
		addUserPage.enterFirstName(firstName);
		Thread.sleep(1000);
		addUserPage.enterLastName(lastName);
		Thread.sleep(1000);
		addUserPage.enterEmail(email);
		Thread.sleep(1000);
		addUserPage.enterJobTitle(jobTitle);
		Thread.sleep(1000);

		// Select role
		addUserPage.selectUserRole();
		addUserPage.selectUserRole2();
		Thread.sleep(2000);

		// Click on "Add User" button to submit the form
		addUserPage.clickAddUser();
		Thread.sleep(2000);

		// Verify if user is successfully added by checking redirection or a success
		// message
		String currentUrl = driver.getCurrentUrl();
		String expectedUrl = "https://meet2.synesisit.info/user-management"; // Expected URL after adding a user

		Assert.assertEquals(currentUrl, expectedUrl, "User not added successfully, URL did not match expected.");
	}

	@AfterMethod
	public void captureFailureScreenshot2(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}

	@Test(priority = 3) // Test case for adding an owner
	void testAddUser3() throws InterruptedException {
		// Navigate to the Add User page after login
		AddSingleUser_Page addUserPage = new AddSingleUser_Page(driver);

		// Open the Admin dropdown
		addUserPage.clickAdminDropdown();
		Thread.sleep(2000);

		// Click on "Manage Users"
		addUserPage.clickManageUsers();
		Thread.sleep(2000);

		// Click on "Add Single User"
		addUserPage.clickAddSingleUser();
		Thread.sleep(2000);

		// Load the new sheet for user data
		AddSingleUserSheet = ExcelWBook.getSheetAt(4);

		// Reading user data from the Excel sheet
		String firstName = AddSingleUserSheet.getRow(2).getCell(0).toString();
		String lastName = AddSingleUserSheet.getRow(2).getCell(1).toString();
		String email = AddSingleUserSheet.getRow(2).getCell(2).toString();
		String jobTitle = AddSingleUserSheet.getRow(2).getCell(3).toString();

		// Fill out the user information
		addUserPage.enterFirstName(firstName);
		Thread.sleep(1000);
		addUserPage.enterLastName(lastName);
		Thread.sleep(1000);
		addUserPage.enterEmail(email);
		Thread.sleep(1000);
		addUserPage.enterJobTitle(jobTitle);
		Thread.sleep(1000);

		// Select role
		addUserPage.selectUserRole();
		addUserPage.selectUserRole3();
		Thread.sleep(2000);

		// Click on "Add User" button to submit the form
		addUserPage.clickAddUser();
		Thread.sleep(2000);

		// Verify if user is successfully added by checking redirection or a success
		// message
		String currentUrl = driver.getCurrentUrl();
		String expectedUrl = "https://meet2.synesisit.info/user-management"; // Expected URL after adding a user

		Assert.assertEquals(currentUrl, expectedUrl, "User not added successfully, URL did not match expected.");
	}

	@AfterMethod
	public void captureFailureScreenshot3(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}

	@Test(priority = 4) // Test case for verify the error message
	void testAddUser4() throws InterruptedException {
		// Navigate to the Add User page after login
		AddSingleUser_Page addUserPage = new AddSingleUser_Page(driver);

		// Open the Admin dropdown
		addUserPage.clickAdminDropdown();
		Thread.sleep(2000);

		// Click on "Manage Users"
		addUserPage.clickManageUsers();
		Thread.sleep(2000);

		// Click on "Add Single User"
		addUserPage.clickAddSingleUser();
		Thread.sleep(2000);

		// Load the new sheet for user data
		AddSingleUserSheet = ExcelWBook.getSheetAt(3);

		// Reading user data from the Excel sheet
		String firstName = AddSingleUserSheet.getRow(1).getCell(0).toString();
		String lastName = AddSingleUserSheet.getRow(1).getCell(1).toString();
		String email = AddSingleUserSheet.getRow(1).getCell(2).toString();
		String jobTitle = AddSingleUserSheet.getRow(1).getCell(3).toString();

		// Fill out the user information
		addUserPage.enterFirstName(firstName);
		Thread.sleep(1000);
		addUserPage.enterLastName(lastName);
		Thread.sleep(1000);
		addUserPage.enterEmail(email);
		Thread.sleep(1000);
		addUserPage.enterJobTitle(jobTitle);
		Thread.sleep(1000);

		// Select role
		addUserPage.selectUserRole();
		Thread.sleep(2000);

		// Click on "Add User" button to submit the form
		addUserPage.clickAddUser();
		Thread.sleep(2000);

		// Get the error message and verify it
		AddSingleUser_Page AddSingleUser_Page = new AddSingleUser_Page(driver);
		String actualErrorMessage = AddSingleUser_Page.getErrorMessage();
		String expectedErrorMessage = "User already exist";

		Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message did not match.");
	}

	@AfterMethod
	public void captureFailureScreenshot4(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}

	@Test(priority = 5) // Test case for verify the success message
	void testAddUser5() throws InterruptedException {
		// Navigate to the Add User page after login
		AddSingleUser_Page addUserPage = new AddSingleUser_Page(driver);

		// Open the Admin dropdown
		addUserPage.clickAdminDropdown();
		Thread.sleep(2000);

		// Click on "Manage Users"
		addUserPage.clickManageUsers();
		Thread.sleep(2000);

		// Click on "Add Single User"
		addUserPage.clickAddSingleUser();
		Thread.sleep(2000);

		// Load the new sheet for user data
		AddSingleUserSheet = ExcelWBook.getSheetAt(3);

		// Reading user data from the Excel sheet
		String firstName = AddSingleUserSheet.getRow(2).getCell(0).toString();
		String lastName = AddSingleUserSheet.getRow(2).getCell(1).toString();
		String email = AddSingleUserSheet.getRow(2).getCell(2).toString();
		String jobTitle = AddSingleUserSheet.getRow(2).getCell(3).toString();

		// Fill out the user information
		addUserPage.enterFirstName(firstName);
		Thread.sleep(1000);
		addUserPage.enterLastName(lastName);
		Thread.sleep(1000);
		addUserPage.enterEmail(email);
		Thread.sleep(1000);
		addUserPage.enterJobTitle(jobTitle);
		Thread.sleep(1000);

		// Select role
		addUserPage.selectUserRole();
		Thread.sleep(2000);

		// Click on "Add User" button to submit the form
		addUserPage.clickAddUser();
		Thread.sleep(2000);

		// Get the success message and verify it
		AddSingleUser_Page AddSingleUser_Page = new AddSingleUser_Page(driver);
		String actualErrorMessage = AddSingleUser_Page.getSuccessMessage();
		String expectedErrorMessage = "Invitations Sent";

		Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Success message did not match.");
	}

	@AfterMethod
	public void captureFailureScreenshot5(ITestResult result) throws IOException {
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