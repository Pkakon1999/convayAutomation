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

import Page_Objects.FeatureUXValidation_Page;
import Utilities.Take_Screenshot;

public class FeatureUXValidation {

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

	@Test(priority = 1) // Test case for Validate Feature page
	void HomeUX() throws InterruptedException {

		// Click on Feature button from landing page
		FeatureUXValidation_Page feature = new FeatureUXValidation_Page(driver);
		
		Thread.sleep(2000);
		feature.clickAccept();
		Thread.sleep(2000);

		feature.clickFeature();
		Thread.sleep(2000);

		// Use JavaScriptExecutor to scroll down by 10000 pixels
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,10000);");
		Thread.sleep(3000);

		// Get and print the current URL
		String currentUrl = driver.getCurrentUrl();
		System.out.println("Current URL: " + currentUrl);

		// User redirection validation after click on Feature button
		String expectedUrl = "https://convay.com/features";

		Assert.assertEquals(currentUrl, expectedUrl, "URL did not match the expected URL.");
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