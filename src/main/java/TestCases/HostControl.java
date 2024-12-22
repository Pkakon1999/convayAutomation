package TestCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Page_Objects.HostControl_Page;
import Page_Objects.Login_Page;
import Utilities.Take_Screenshot;

public class HostControl {

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
		Thread.sleep(3000); // Wait for login to complete
	}

	@Test(priority = 1) // Test case to access Host Control from Scheduler
	void Host_Control() throws InterruptedException {
	    // Navigate to the Manage User page after login
	    HostControl_Page control = new HostControl_Page(driver);

	    // Click on OpenScheduler
	    control.clickScheduler();
	    Thread.sleep(3000);

	    // Click on Host Control
	    control.clickHostControl();
	    Thread.sleep(3000);

	    // Get the scrollable card container
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    WebElement cardContainer = control.getCardContainer();

	    // Scroll down the card container incrementally
	    int scrollAmount = 100; // Adjust this value for smaller steps
	    int scrollDuration = 200; // Delay in milliseconds between scroll steps

	    for (int i = 0; i < 30; i++) { // Adjust the number of iterations as needed
	        js.executeScript("arguments[0].scrollBy(0, arguments[1]);", cardContainer, scrollAmount);
	        Thread.sleep(scrollDuration);
	    }

	    // Click on Cancel button
	    control.clickCancel();
	    Thread.sleep(2000);
	}


	@AfterMethod
	public void captureFailureScreenshot1(ITestResult result) throws IOException {
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