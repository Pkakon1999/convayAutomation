package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class MeetingManagementPrevious_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Define locators
	By meetingManagement_loc = By.xpath("//span[normalize-space()='Meeting Management']");
	By btn_previousMeeting = By.xpath("//a[@id='previous-meeting']");

	// Constructor
	public MeetingManagementPrevious_Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on Meeting Management
	public void clickMeetingManagement() {
		driver.findElement(meetingManagement_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on previous meeting
	public void clickPreviousMeetings() {
		driver.findElement(btn_previousMeeting).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to get the text of "Previous Meeting" button
	public String getButtonText() {
		WebElement buttonElement = driver.findElement(btn_previousMeeting);
		return buttonElement.getText();
	}

	// Method to validate "Previous Meeting" button text
	public void validateButtonText(String expectedText) {
		String actualText = getButtonText();
		Assert.assertEquals(actualText, expectedText, "Previous Meetings");
	}
}
