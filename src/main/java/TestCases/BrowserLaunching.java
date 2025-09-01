package TestCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import Utilities.Take_Screenshot;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserLaunching {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup(); // chrome driver
		WebDriver driver = new ChromeDriver();

//		WebDriverManager.firefoxdriver().setup();
//		WebDriver driver1 = new FirefoxDriver();
//		
		driver.manage().window().maximize();
		driver.get("https://convay.com/sign-in");

		Thread.sleep(1000);
		
		

		driver.close();

	}

}
