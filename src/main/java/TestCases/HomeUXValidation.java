package TestCases;

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

import Page_Objects.HomeUXValidation_Page;
import Utilities.Take_Screenshot;

public class HomeUXValidation {

	WebDriver driver;
	XSSFWorkbook ExcelWBook;
	XSSFSheet ExcelWSheet;

	@BeforeClass
	void setup() throws IOException {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}

	@BeforeMethod
	void navigateToSignInPage() {
		driver.get("https://convay.com/");
	}

	@Test(priority = 1) // Test case for Validate Home page
	void HomeUX() throws InterruptedException {

		// Click on Home button from landing page
		HomeUXValidation_Page home = new HomeUXValidation_Page(driver);
		
		Thread.sleep(2000);
		home.clickAccept();
		Thread.sleep(2000);

		home.clickHome();
		Thread.sleep(2000);

		// Use JavaScriptExecutor to scroll down by 10000 pixels
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,10000);");
		Thread.sleep(3000);

		// Get and print the current URL
		String currentUrl = driver.getCurrentUrl();
		System.out.println("Current URL: " + currentUrl);

		// User redirection validation after click on Home button
		String expectedUrl = "https://convay.com/";

		Assert.assertEquals(currentUrl, expectedUrl, "URL after login did not match the expected URL.");
	}

	@AfterMethod
	public void Aftermethod1(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			Take_Screenshot.TakeScreenshot(driver, result.getName());
		}
	}

	@AfterClass
	void teardown() throws IOException {
		driver.close();
	}

}