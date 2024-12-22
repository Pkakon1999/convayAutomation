package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class InstantMeetingUX_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Define locators
	By meetingID_loc = By.xpath("//span[normalize-space()='6477 1284 0966']");
	By btn_startInstantMeeting = By.xpath("//button[normalize-space()='Start Now']");
	By btn_Start = By.xpath("//button[@id='startButton']");
	By btn_cross_loc = By.xpath("//div[@class='cnv-start-up-modal-left']");

	// Constructor
	public InstantMeetingUX_Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to get the meeting ID of the instant meeting field
	public String getID() {
		return driver.findElement(meetingID_loc).getAttribute("value");
	}

	// Method to get the text of "Start Instant Meeting" button
	public String getInstantButtonText() {
		WebElement buttonElement = driver.findElement(btn_startInstantMeeting);
		return buttonElement.getText();
	}

	// Method to validate "Start Instant Meeting" button text
	public void validateButtonText(String expectedText) {
		String actualText = getInstantButtonText();
		Assert.assertEquals(actualText, expectedText, "Start Instant Meeting");
	}

	// Method to click on "Start Instant Meeting"
	public void clickInstantMeeting() {
		driver.findElement(btn_startInstantMeeting).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to get the text of "Start" button
	public String getStartButtonText() {
		WebElement buttonElement = driver.findElement(btn_Start);
		return buttonElement.getText();
	}

	// Method to validate "Start" button text
	public void validateStartButtonText(String expectedText) {
		String actualText = getStartButtonText();
		Assert.assertEquals(actualText, expectedText, "Start");
	}

	// Method to click on cross button
	public void clickCross() {
		driver.findElement(btn_cross_loc).click();
	}

}
