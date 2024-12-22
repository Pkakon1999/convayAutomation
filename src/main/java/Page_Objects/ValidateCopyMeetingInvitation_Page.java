package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ValidateCopyMeetingInvitation_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Define locators
	By threeDot_loc = By.xpath("//span[contains(@class,'cnv-btn-action-container')]//span//*[name()='svg']");
	By copy_loc = By.xpath("//a[normalize-space()='Copy']");
	By toaster_Message = By.xpath("//div[@class='cnv-toast-body']");

	// Constructor
	public ValidateCopyMeetingInvitation_Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on three dot
	public void clickThreeDot() {
		driver.findElement(threeDot_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on Copy
	public void clickCopy() {
		driver.findElement(copy_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public String getToasterValue() {
		// Wait for the toaster message to be visible and return its text
		WebElement toasterMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(toaster_Message));
		return toasterMessageElement.getText();
	}

}
