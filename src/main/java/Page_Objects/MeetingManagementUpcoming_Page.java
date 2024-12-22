package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class MeetingManagementUpcoming_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Define locators
	By meetingManagement_loc = By.xpath("//span[normalize-space()='Meeting Management']");
	By btn_upcomingMeeting = By.xpath("//a[@id='upcoming-meeting']");

	// Constructor
	public MeetingManagementUpcoming_Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on Meeting Management
	public void clickMeetingManagement() {
		driver.findElement(meetingManagement_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on upcoming meeting
	public void clickUpcomingMeetings() {
		driver.findElement(btn_upcomingMeeting).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to get the text of "Upcoming Meeting" button
	public String getButtonText() {
		WebElement buttonElement = driver.findElement(btn_upcomingMeeting);
		return buttonElement.getText();
	}

	// Method to validate "Upcoming Meeting" button text
	public void validateButtonText(String expectedText) {
		String actualText = getButtonText();
		Assert.assertEquals(actualText, expectedText, "Upcoming Meetings");
	}
}
