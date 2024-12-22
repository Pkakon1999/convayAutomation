package TestCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Page_Objects.Login_Page;
import Page_Objects.ValidateMonthView_Page;
import Utilities.Take_Screenshot;

public class ValidateMonthView {

	WebDriver driver;
	XSSFWorkbook ExcelWBook;
	XSSFSheet ExcelWSheet;
	XSSFSheet AddSingleUserSheet;

	// Inside the setup method
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
	void navigateToHomePage2() throws InterruptedException {
		// Login before add users
		driver.get("https://meet2.synesisit.info/sign-in");

		AddSingleUserSheet = ExcelWBook.getSheetAt(18);

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

	@Test(priority = 1) // Test case to validate Convay meeting calender (Month View)
	void MonthView() throws InterruptedException {
		// Navigate to the invitation card after login
		ValidateMonthView_Page month = new ValidateMonthView_Page(driver);

		// Click on Open Scheduler
		month.clickScheduler();
		Thread.sleep(2000);

		// Click on Cancel from Scheduler card
		month.clickCancel();
		Thread.sleep(2000);

		// Click on Month button
		month.selectMonth();
		Thread.sleep(2000);

		// To validate Month button text
		String expectedTextMonth = "Month";
		month.validateMonthButtonText(expectedTextMonth);
		Thread.sleep(2000);

	}

	@AfterMethod
	public void captureFailureScreenshot2(ITestResult result) throws IOException {
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