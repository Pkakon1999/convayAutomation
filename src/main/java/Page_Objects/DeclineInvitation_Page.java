package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DeclineInvitation_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Define locators
	By btn_decline = By.xpath("//span[normalize-space()='Decline']");
	By btn_cancel = By.xpath("//button[@class='cnv-btn cnv-btn-cancel-new cnv-mr-3']");
	By btn_decline_loc = By.xpath("//button[normalize-space()='Decline']");
	By toaster_Message = By.xpath("//div[@class='cnv-toast-body']");

	// Constructor
	public DeclineInvitation_Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on Decline button from invitation card
	public void clickDeclineInvitation() {
		driver.findElement(btn_decline).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on Cancel button
	public void clickCancel() {
		driver.findElement(btn_cancel).click();
	}

	// Method to click on Decline button
	public void clickDecline() {
		driver.findElement(btn_decline_loc).click();
	}

	public String getToasterValue() {
		// Wait for the toaster message to be visible and return its text
		WebElement toasterMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(toaster_Message));
		return toasterMessageElement.getText();
	}

}
