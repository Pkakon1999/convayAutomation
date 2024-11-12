package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomeUXValidation_Page {

	WebDriver driver;

	// Constructors
	public HomeUXValidation_Page(WebDriver driver) {
		this.driver = driver;
	}

	// Locators

	By btn_home_loc = By.xpath("//a[normalize-space()='Home']");
	By btn_acceptCookies_loc = By.xpath("//button[@data-cky-tag='accept-button']");

	// Action methods

	// Method to click on Home button
	public void clickHome() {
		driver.findElement(btn_home_loc).click();
	}

	// Method to click on Accept Cookie button
	public void clickAccept() {
		driver.findElement(btn_acceptCookies_loc).click();
	}

}
