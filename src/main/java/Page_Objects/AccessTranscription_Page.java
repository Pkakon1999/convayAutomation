package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class AccessTranscription_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Define locators
	By meetingManagement_loc = By.xpath("//span[normalize-space()='Meeting Management']");
	By selectPreviousMeeting_loc = By.xpath("//a[@id='previous-meeting']");
	By selectMeeting_loc = By.xpath("//div[@class='cnv-flex-one']");
	By transcription_loc = By.xpath("//a[@id='transcription']");
	By meetingMinutes_loc = By.xpath("//div[@class='trans-btn-options-First']");
	
	
	// Constructor
	public AccessTranscription_Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on Meeting Management
	public void clickMeetingManagement() {
		driver.findElement(meetingManagement_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on Previous Meeting
	public void clickPreviousMeeting() {
		driver.findElement(selectPreviousMeeting_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to select a scheduled meeting to edit
	public void selectMeeting() {
		driver.findElement(selectMeeting_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on Transcription tab
	public void clickTranscription() {
		driver.findElement(transcription_loc).click();
	}

	// Method to get the text of "Generate Meeting Minutes" button
	public String getButtonText() {
		WebElement buttonElement = driver.findElement(meetingMinutes_loc);
		return buttonElement.getText();
	}

	// Method to validate "Generate Meeting Minutes" button text
	public void validateButtonText(String expectedText) {
		String actualText = getButtonText();
		Assert.assertEquals(actualText, expectedText, "Generate Meeting Minutes");
	}

}
