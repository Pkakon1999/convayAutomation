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
import Page_Objects.ResendInvitation_Page;
import Utilities.Take_Screenshot;

public class ResendInvitation {

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

	@Test(priority = 1) // Test case to check If select No, Resend invitation is working or not
	void ResendInvitation1() throws InterruptedException {
		// Navigate to the Add User page after login
		ResendInvitation_Page resendInvitationPage = new ResendInvitation_Page(driver);

		// Open the Admin dropdown
		resendInvitationPage.clickAdminDropdown();
		Thread.sleep(2000);

		// Click on "Manage Users"
		resendInvitationPage.clickManageUsers();
		Thread.sleep(2000);

		// Click on the role dropdown
		Thread.sleep(2000);
		resendInvitationPage.roleDropdown();
		Thread.sleep(2000);

		// Select Invited from dropdown
		resendInvitationPage.selectInvited();
		Thread.sleep(2000);

		// Click on 3 dot for which user you want to resend invitation
		resendInvitationPage.select3dot();
		Thread.sleep(2000);

		// Click on Resend
		resendInvitationPage.selectResend();
		Thread.sleep(2000);

		// Click on No to cancel the resend invitation
		resendInvitationPage.selectNo();
		Thread.sleep(2000);
	}

	@AfterMethod
	public void captureFailureScreenshot1(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}
	
	
	@Test(priority = 2) // Test case to check If select No, Resend invitation is working or not
	void ResendInvitation2() throws InterruptedException {
		// Navigate to the Add User page after login
		ResendInvitation_Page resendInvitationPage = new ResendInvitation_Page(driver);

		// Open the Admin dropdown
		resendInvitationPage.clickAdminDropdown();
		Thread.sleep(2000);

		// Click on "Manage Users"
		resendInvitationPage.clickManageUsers();
		Thread.sleep(2000);

		// Click on the role dropdown
		Thread.sleep(2000);
		resendInvitationPage.roleDropdown();
		Thread.sleep(2000);

		// Select Invited from dropdown
		resendInvitationPage.selectInvited();
		Thread.sleep(2000);

		// Click on 3 dot for which user you want to resend invitation
		resendInvitationPage.select3dot();
		Thread.sleep(2000);

		// Click on Resend
		resendInvitationPage.selectResend();
		Thread.sleep(2000);

		// Click on Yes to resend invitation
		resendInvitationPage.selectYes();
		Thread.sleep(2000);

		String getToasterValue = resendInvitationPage.getToasterValue();

		// Verify the toaster message text
		Assert.assertEquals(getToasterValue, "Invitation resent");
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