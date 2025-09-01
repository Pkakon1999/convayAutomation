package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class MeetingManagementUX_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Define locators
	By meetingManagement_loc = By.xpath("//span[normalize-space()='Meeting Management']");
	By selectUpcomingMeeting_loc = By.xpath("//a[normalize-space()='Upcoming Meetings']");
	By selectPreviousMeeting_loc = By.xpath("//a[normalize-space()='Previous Meeting']");
	By upcomingButton_loc = By.xpath("//a[normalize-space()='Upcoming Meetings']");
	By previousButton_loc = By.xpath("//a[normalize-space()='Previous Meeting']");

	// Constructor
	public MeetingManagementUX_Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on Meeting Management
	public void clickMeetingManagement() {
		driver.findElement(meetingManagement_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on Upcoming Meeting
	public void clickUpcomingMeeting() {
		driver.findElement(selectUpcomingMeeting_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on Previous Meeting
	public void clickPreviousMeeting() {
		driver.findElement(selectPreviousMeeting_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to get the text of "Upcoming Meeting" button
	public String getUpcomingButtonText() {
		WebElement buttonElement = driver.findElement(upcomingButton_loc);
		return buttonElement.getText();
	}

	// Method to validate "Upcoming Meeting" button text
	public void validateUpcomingButtonText(String expectedText) {
		String actualText = getUpcomingButtonText();
		Assert.assertEquals(actualText, expectedText, "Upcoming Meetings");
	}

	// Method to get the text of "Previous Meeting" button
	public String getPreviousButtonText() {
		WebElement buttonElement = driver.findElement(previousButton_loc);
		return buttonElement.getText();
	}

	// Method to validate "Previous Meeting" button text
	public void validatePreviousButtonText(String expectedText) {
		String actualText = getPreviousButtonText();
		Assert.assertEquals(actualText, expectedText, "Previous Meetings");
	}

}
