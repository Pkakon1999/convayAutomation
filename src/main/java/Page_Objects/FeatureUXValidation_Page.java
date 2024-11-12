package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FeatureUXValidation_Page {

	WebDriver driver;

	// Constructors
	public FeatureUXValidation_Page(WebDriver driver) {
		this.driver = driver;
	}

	// Locators

	By btn_feature_loc = By.xpath("//a[@class='nav-link'][normalize-space()='Features']");
	By btn_acceptCookies_loc = By.xpath("//button[@data-cky-tag='accept-button']");

	// Action methods

	// Method to click on Feature button
	public void clickFeature() {
		driver.findElement(btn_feature_loc).click();
	}

	// Method to click on Accept Cookie button
	public void clickAccept() {
		driver.findElement(btn_acceptCookies_loc).click();
	}

}
