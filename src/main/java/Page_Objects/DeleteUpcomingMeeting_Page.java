package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DeleteUpcomingMeeting_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Define locators
	By threeDot_loc = By.xpath("//span[contains(@class,'cnv-btn-action-container')]//span//*[name()='svg']");
	By delete_loc = By.xpath("//a[normalize-space()='Delete']");
	By cancel_loc = By.xpath("//button[@class='cnv-btn cnv-btn-cancel-new cnv-mr-3']");
	By deleteConfirm_loc = By.xpath("//button[normalize-space()='Delete']");
	By upcomingField_loc = By.xpath("//div[@class='cnv-pl-3 cnv-pt-2']");

	// Constructor
	public DeleteUpcomingMeeting_Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on three dot
	public void clickThreeDot() {
		driver.findElement(threeDot_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on Delete
	public void clickDelete() {
		driver.findElement(delete_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on Cancel
	public void clickCancel() {
		driver.findElement(cancel_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on Delete Confirm
	public void clickDeleteConfirm() {
		driver.findElement(deleteConfirm_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to get the value of the Upcoming meeting field
	public String getValue() {
		return driver.findElement(upcomingField_loc).getAttribute("value");
	}

}
