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
import Page_Objects.SearchUser_Page;
import Utilities.Take_Screenshot;

public class SearchUser {

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

	@Test(priority = 1) // Test case for search a user by name
	void searchUserByName() throws InterruptedException {
		// Navigate to the Add User page after login
		SearchUser_Page searchUserPage = new SearchUser_Page(driver);

		// Open the Admin dropdown
		searchUserPage.clickAdminDropdown();
		Thread.sleep(2000);

		// Click on "Manage Users"
		searchUserPage.clickManageUsers();
		Thread.sleep(2000);

		// Load the new sheet for user data
		AddSingleUserSheet = ExcelWBook.getSheet("Search_User");

		// Reading user data from the Excel sheet
		String search = AddSingleUserSheet.getRow(0).getCell(0).toString();

		// Click on "Search User"
		Thread.sleep(2000);
		searchUserPage.searchUser(search);
		Thread.sleep(2000);

	}

	@AfterMethod
	public void captureFailureScreenshot1(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}
	
	@Test(priority = 2) // Test case for search a user by email
	void searchUserByEmail() throws InterruptedException {
		// Navigate to the Add User page after login
		SearchUser_Page searchUserPage = new SearchUser_Page(driver);

		// Open the Admin dropdown
		searchUserPage.clickAdminDropdown();
		Thread.sleep(2000);

		// Click on "Manage Users"
		searchUserPage.clickManageUsers();
		Thread.sleep(2000);

		// Load the new sheet for user data
		AddSingleUserSheet = ExcelWBook.getSheet("Search_User");

		// Reading user data from the Excel sheet
		String search = AddSingleUserSheet.getRow(0).getCell(1).toString();

		// Click on "Search User"
		Thread.sleep(2000);
		searchUserPage.searchUser(search);
		Thread.sleep(2000);

	}

	@AfterMethod
	public void captureFailureScreenshot2(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}
	
	@Test(priority = 3) // Test case for search a user by job title
	void searchUserByJobTitle() throws InterruptedException {
		// Navigate to the Add User page after login
		SearchUser_Page searchUserPage = new SearchUser_Page(driver);

		// Open the Admin dropdown
		searchUserPage.clickAdminDropdown();
		Thread.sleep(2000);

		// Click on "Manage Users"
		searchUserPage.clickManageUsers();
		Thread.sleep(2000);

		// Load the new sheet for user data
		AddSingleUserSheet = ExcelWBook.getSheet("Search_User");

		// Reading user data from the Excel sheet
		String search = AddSingleUserSheet.getRow(0).getCell(2).toString();

		// Click on "Search User"
		Thread.sleep(2000);
		searchUserPage.searchUser(search);
		Thread.sleep(2000);

	}

	@AfterMethod
	public void captureFailureScreenshot3(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}
	
	@Test(priority = 4) // Test case for search a user by role
	void searchUserByRole() throws InterruptedException {
		// Navigate to the Add User page after login
		SearchUser_Page searchUserPage = new SearchUser_Page(driver);

		// Open the Admin dropdown
		searchUserPage.clickAdminDropdown();
		Thread.sleep(2000);

		// Click on "Manage Users"
		searchUserPage.clickManageUsers();
		Thread.sleep(2000);

		// Load the new sheet for user data
		AddSingleUserSheet = ExcelWBook.getSheet("Search_User");

		// Reading user data from the Excel sheet
		String search = AddSingleUserSheet.getRow(0).getCell(3).toString();

		// Click on "Search User"
		Thread.sleep(2000);
		searchUserPage.searchUser(search);
		Thread.sleep(2000);

	}

	@AfterMethod
	public void captureFailureScreenshot4(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}
	
	@Test(priority = 5) // Test case for search a user by status
	void searchUserByStatus() throws InterruptedException {
		// Navigate to the Add User page after login
		SearchUser_Page searchUserPage = new SearchUser_Page(driver);

		// Open the Admin dropdown
		searchUserPage.clickAdminDropdown();
		Thread.sleep(2000);

		// Click on "Manage Users"
		searchUserPage.clickManageUsers();
		Thread.sleep(2000);

		// Load the new sheet for user data
		AddSingleUserSheet = ExcelWBook.getSheet("Search_User");

		// Reading user data from the Excel sheet
		String search = AddSingleUserSheet.getRow(0).getCell(4).toString();

		// Click on "Search User"
		Thread.sleep(2000);
		searchUserPage.searchUser(search);
		Thread.sleep(2000);

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